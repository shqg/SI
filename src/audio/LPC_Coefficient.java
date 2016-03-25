package audio;

import java.util.ArrayList;
import java.util.List;


public class LPC_Coefficient {
    //	 int N = 512;
    public List<List<double[]>> lpc_lists = new ArrayList<List<double[]>>();

    public void lpcfiles(List<List<double[]>> alleFrames) {
        int p = 12;
        List<double[]> Frames;

        for (int i = 0; i < alleFrames.size(); i++) {
            Frames = alleFrames.get(i);

            List<double[]> lpcCoeff = new ArrayList<double[]>(Frames.size());

            for (double[] Frame : Frames) {
                lpcCoeff.add(lpccoefficient(Frame, p));
            }

            lpc_lists.add(lpcCoeff);
        }
    }

    public double[] lpccoefficient(double[] Frame, int p) {
        //		Autokorrelationsfunktion im l-ten Frame berechnen		
        double[] r = new double[p + 1];

        for (int m = 0; m < (p + 1); m++) {
            for (int n = 0; n <= (Frame.length - 1 - m); n++) {
                r[m] += (Frame[n] * Frame[n + m]);
            }
        }

        //return r;
        //LPC-Koeffizienten
        double[][] alpha = new double[p + 1][p + 1];
        double[] E = new double[p + 1];
        double[] LPC = new double[p];
        alpha[0][0] = 0;
        E[0] = r[0];

        for (int i = 1; i <= p; i++) {
            double k = r[i];

            for (int j = 1; j < i; j++) {
                k -= (alpha[i - 1][j] * r[i - j]);
            }

            k /= E[i - 1];
            alpha[i][i] = k;

            for (int j = 1; j < i; j++) {
                alpha[i][j] = alpha[i - 1][j] - (k * alpha[i - 1][i - j]);
            }

            E[i] = (1.0 - (k * k)) * E[i - 1];
        }

        for (int i = 1; i <= p; i++) {
            LPC[i - 1] = alpha[p][i];
        }

        return LPC;
    }
}
