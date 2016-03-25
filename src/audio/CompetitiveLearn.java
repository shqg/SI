package audio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CompetitiveLearn {

      public List<double[]> Kom_lerenen(List<double[]> Frames, int k,double l){
													     //k=clusterzhal l=lernrate	
    	 List<double[]> kl = new ArrayList<double[]>(k);
         // Prototypen initialisieren
    	 
         for (int i = 0; i < k; i++) {
             Random random = new Random();
             int randomNum = k + random.nextInt(Frames.size() - k);
             kl.add(Frames.get(randomNum));
          }
		
		 for (int a = 0; a < 20; a++) {

	            for (double[] Frame : Frames) { //durch alle Frames.size() Frames 

	                double min = Double.MAX_VALUE;//minimum Abstand
	                int argmin = 0;

	                for (int i = 0; i < k; i++) { //durch alle k prototypen(k ca.100)
	                    double abstand = 0;

	                    for (int j = 0; j < Frame.length; j++) {// berechne abstand zwischen frame und prototyp
	                        abstand += Math.pow(Frame[j] - kl.get(i)[j], 2.0);
	                    }

	                    if (abstand < min) {
	                        min = abstand;
	                        argmin = i;
	                    }

	                }

	                for (int j = 0; j < Frame.length; j++) {
	                    kl.get(argmin)[j] = kl.get(argmin)[j] + l * (Frame[j] - kl.get(argmin)[j]);
	                }

	            }
	        }

	        return kl;
	    }
	
}
