package audio.test01;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioReader {
	
	public List<String> list;
    public List<List<double[]>> alleDateienFrames;
    public  double MAX_16_BIT = Short.MAX_VALUE;
	public AudioReader()
	{		
		this.list = new ArrayList<String>();
 
		list.add("audiofiles/audiomerge/gefujiang/001.wav"); // 0 Test1
		list.add("audiofiles/audiomerge/gefujiang/004.wav");
		list.add("audiofiles/audiomerge/lu/001.wav"); // 2 Test2
		list.add("audiofiles/audiomerge/lu/004.wav");
		list.add("audiofiles/audiomerge/shixiaobin/001.wav"); // 4 Test3
		list.add("audiofiles/audiomerge/shixiaobin/004.wav");
		list.add("audiofiles/audiomerge/yangqichuan/001.wav"); // 6 Test4
		list.add("audiofiles/audiomerge/yangqichuan/004.wav");
		list.add("audiofiles/audiomerge/zhoudan/001.wav"); //8 Test5
		list.add("audiofiles/audiomerge/zhoudan/004.wav");
		list.add("audiofiles/audiomerge/zhoulei/001.wav"); //10 Test6
		list.add("audiofiles/audiomerge/zhoulei/004.wav");
		list.add("audiofiles/audiomerge/gengshuoqin/001.wav"); // 11 Test7
		list.add("audiofiles/audiomerge/gengshuoqin/004.wav");
		
		
		//////////////////////////////////////
		
 
		 
		
		
		this.alleDateienFrames = new ArrayList<List<double[]>>();
		this.audioreader();
	}
	
	public void audioreader(){
		
		for(int i = 0; i <list.size(); i++){
			File fileIn=new File(list.get(i));
			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fileIn);
				int bytesPerFrame = audioInputStream.getFormat().getFrameSize();
				int frameRate = java.lang.Math.round(audioInputStream.getFormat().getFrameRate());
				int numBytes = (int)audioInputStream.getFrameLength()* bytesPerFrame; 
   			
				AudioFormat audioFormat = audioInputStream.getFormat();
				int FrameSize = audioFormat.getFrameSize();
				// infos ausgeben zu einer audiofile (hz, bit, usw.)
				System.out.println("Gelesene Audiofile: "+fileIn);
				System.out.println("Format: "+audioInputStream.getFormat().toString());
//				System.out.println("Anzahl Bytes: "+numBytes);
//				System.out.println("Anzahl: "+audioInputStream.getFrameLength());
  			 		
   				byte[] audioBytes = new byte[numBytes];
  				int totalFramesRead = 0;
    			int numBytesRead = 0;
    			int numFramesRead = 0;
    			// Try to read numBytes bytes from the file.
    			while ((numBytesRead = audioInputStream.read(audioBytes)) != -1) {
      				// Calculate the number of frames actually read.
      				//numFramesRead = numBytesRead / bytesPerFrame;
    				numFramesRead = numBytesRead /FrameSize;//
      				totalFramesRead += numFramesRead;
    			}
    			
//  			   System.out.println("Fertig.");	
  			   double[] byteTodouble = new double[totalFramesRead];//new 
  			  
  			   for (int t = 0; t < byteTodouble.length; t++) {
               
                byteTodouble[t] = ((short) (((audioBytes[2*t+1] & 0xFF) << 8)
                		+ (audioBytes[2*t] & 0xFF))) / ((double) MAX_16_BIT);
  			   }
//  			   System.out.println("Anzahl double: " + byteTodouble.length);

  			   int N = 512;
  			   int M = 256;
            
  			   List<double[]> Frames = new ArrayList<double[]>();
  			   for (int a = 0; a < byteTodouble.length - N; a += M) {
  				   double[] Frame = new double[N];
  				   for (int b = 0; b < N; b++) {
  					   Frame[b] = byteTodouble[a + b];
  				   }
  				   Frames.add(Frame);
  			   }
  			   System.out.println("Anzahl Frames: " + Frames.size());
  			   
  			   alleDateienFrames.add(Frames);
            
  			}catch (UnsupportedAudioFileException e) { 
  				// Handle the error...
  				e.printStackTrace();
				} catch (IOException e) {

	                e.printStackTrace();
	            }
		}
	}

}
