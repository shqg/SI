package audio.test01;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.sound.sampled.*;

import audio.CL_MFCC;
import audio.HammingWindow;
import audio.MFCC_Coefficient;
import comirva.audio.util.MFCC;

/**
 * Klasse fuer direkten Audio-Stream.
 */
public class LiveStream implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean run;
    private byte[] readBuff;
    
    private static double level = 1;

    //get the input audio device
    private TargetDataLine getAudio() throws LineUnavailableException {
        DataLine.Info info;
        AudioFormat format = getAudioFormat();
        info = new DataLine.Info(TargetDataLine.class, format);

        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Error on AudioSystem.isLineSupported(info)");
        }
        return AudioSystem.getTargetDataLine(format);
    }

    //get the input audio format
    private AudioFormat getAudioFormat() {
        return new AudioFormat(16000.0f, 16, 1, true, false); //16kHz 16bit mono identisch zu den waves
    }

    private double[] byteToDouble(byte[] bytes, double level) {
        short tmp;
        double[] out = new double[bytes.length / 2];
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        for (int i = 0; i < out.length; i++) {
            tmp = bb.getShort();
            out[i] = (double) tmp / level;
        }
        return out;
    }

	/**
	 * Online-Klassifikation
	 *
	 * @throws LineUnavailableException
	 */
	public void readLive() throws LineUnavailableException {

		AudioReader ar = new AudioReader();
		HammingWindow hw = new HammingWindow();
		List<List<double[]>> Frames_von_alleVedios = hw.hammingApply(ar.alleDateienFrames);
		final MFCC_Coefficient mc = new MFCC_Coefficient();
		mc.mfcc(Frames_von_alleVedios);
		List<List<double[]>> alleTrainingList2 = new ArrayList<List<double[]>>();
		alleTrainingList2.add(mc.mfcc_lists.get(1));
		alleTrainingList2.add(mc.mfcc_lists.get(3));
		alleTrainingList2.add(mc.mfcc_lists.get(5));
		alleTrainingList2.add(mc.mfcc_lists.get(7));
		alleTrainingList2.add(mc.mfcc_lists.get(9));
		alleTrainingList2.add(mc.mfcc_lists.get(11));
		alleTrainingList2.add(mc.mfcc_lists.get(13));
		CL_MFCC clmfcc = new CL_MFCC();
		clmfcc.mfcc_apply(alleTrainingList2);//clmfcc.kl_Lists £ºoutput

		final MFCC mf = new MFCC((float) 16000.0);
		final TargetDataLine line;
		line = getAudio();
		final int use = 15;
		final int bufferSize = use * 512;

		line.open(getAudioFormat());
		line.flush(); // Eventuell altes Zeug aus dem Puffer werfen
		line.start();

		run = true;
		readBuff = new byte[bufferSize * 2];

		new Thread() {
			@Override
			public void run() {

				double[] frame = new double[512];// length of a Frame
				System.out.println("Start Recording");
				while (run) {
					if (0 != line.read(readBuff, 0, readBuff.length)) {
						DoubleBuffer dbuf = DoubleBuffer.wrap(byteToDouble(readBuff, level));
						int[] frame10 = new int[use];
						for (int i = 0; i < use; i++) {

							for (int j = 0; j < frame.length; j++) {
								frame[j] = dbuf.get();
							}

							double[] mfccframe = mf.processWindow(frame, 0);
							// System.out.println(Arrays.toString(mfccframe));
							// mfcc mit codebuecher vergleichen
							frame10[i] = Vergleichen(clmfcc.kl_Lists, mfccframe);

						}

						System.out.println(Arrays.toString(frame10));

						printErgebnis(frame10);
						System.out.println();

					} else {
						System.out.println("NOT writing data.");
					}

				}
			}
		}.start();

		try {

			Thread.sleep(1 * 6000); // nach 15min automatisch abbrechen

		} catch (InterruptedException e) {
			System.err.println(e);
		}

		line.stop();
		run = false;
	}
    
    public void printErgebnis(int[] a){
    	int countA = 0; int countE = 0;int countF = 0;int countG = 0;
    	int countB = 0;
    	int countC = 0;
    	int countD = 0;
    	
    	for(int i=0;i<a.length;i++){
    		if(a[i] == 0){
    			countA++;
    		}
    		else if(a[i] == 1){
    			countB++;
    		}
    		else if(a[i] == 2){
    			countC++;
    		}
    		else if(a[i] == 3){
    			countD++;
    		}else if(a[i] == 4){
    			countE++;
    		}
    		else if(a[i] == 5){
    			countF++;
    		}else if(a[i] == 6){
    			countG++;
    		}
    	}
    	
    	int countWho[] = {countA,countB,countC,countD,countE,countF,countG};
    	Arrays.sort(countWho);
    	if(countWho[countWho.length-1] == countA){
    		System.out.println("You are A !");
    	}
    	if(countWho[countWho.length-1] == countB){
    		System.out.println("You are B !");
    	}
    	if(countWho[countWho.length-1] == countC){
    		System.out.println(" You are C !");
    	}
    	if(countWho[countWho.length-1] == countD){
    		System.out.println("You are D !");
    	}
    	if(countWho[countWho.length-1] == countE){
    		System.out.println("You are E !");
    	}
    	if(countWho[countWho.length-1] == countF){
    		System.out.println("You are F !");
    	}
    	if(countWho[countWho.length-1] == countG){
    		System.out.println("You are G !");
    	}
    	
    	
    }
    public int Vergleichen(List<List<double[]>> codebooks, double[] mfccframe) {
		int mini = 0;
		double minlen = Double.MAX_VALUE;
		for (int i = 0; i < codebooks.size(); i++) {
			for (double[] d : codebooks.get(i)) {
				double len = 0;
				for (int j = 0; j < d.length; j++) {
					len += Math.pow(d[j] - mfccframe[j], 2);
				}
				if (len <= minlen) {
					minlen = len;
					mini = i;
				}
			}
		}

		return mini;
//		return (int) Math.floor(mini);
    }
    
    
   public static void main(String args[]){
	   LiveStream ls = new LiveStream();
	   try {
		ls.readLive();
	} catch (LineUnavailableException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
   } 
}
