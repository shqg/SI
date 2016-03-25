package audio;

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
//		list.add("audiofiles/1/1.wav"); // 0 Test1
////		list.add("audiofiles/1/1a.wav");// 
//		list.add("audiofiles/1/2.wav");//  1 
////		list.add("audiofiles/1/2a.wav");//  
//		list.add("audiofiles/1/3.wav");//  2
//		
//		list.add("audiofiles/2/1.wav");//  3 test2
////		list.add("audiofiles/2/1a.wav");
//		list.add("audiofiles/2/2.wav");//  4
////		list.add("audiofiles/2/2a.wav");
//		list.add("audiofiles/2/3.wav");//  5
		////////////////////////
		list.add("audiofiles/gefujiang/001.wav"); // 0 Test1
		list.add("audiofiles/gefujiang/002.wav");//  1 
		list.add("audiofiles/gefujiang/003.wav");//  2
		
		list.add("audiofiles/lu/001.wav");//  3 test2
		list.add("audiofiles/lu/002.wav");//  4
		list.add("audiofiles/lu/003.wav");//  5
		//////////////////////////////////////
		
		list.add("audiofiles/shixiaobin/001.wav");//  6 test3
 		list.add("audiofiles/shixiaobin/002.wav");//  7
 		list.add("audiofiles/shixiaobin/003.wav");//  8
		
		list.add("audiofiles/yangqichuan/001.wav");//  9 test4
 		list.add("audiofiles/yangqichuan/002.wav");//  10
 		list.add("audiofiles/yangqichuan/003.wav");//  11
		
		list.add("audiofiles/zhoudan/001.wav");//  12 test 5
		list.add("audiofiles/zhoudan/002.wav");//  13
		list.add("audiofiles/zhoudan/003.wav");//  14  
		
		list.add("audiofiles/6/1.wav");//  15 test 6
		list.add("audiofiles/6/2.wav");//  16
		
		
		list.add("audiofiles/7/2.wav");//  17 test 7
		list.add("audiofiles/7/3.wav");//  18
		 
		list.add("audiofiles/zhoulei/001.wav");//  19 test 8
		list.add("audiofiles/zhoulei/002.wav");//  20
		list.add("audiofiles/zhoulei/003.wav");//  21
		
		
		list.add("audiofiles/9/1.wav");//  22 test9
		list.add("audiofiles/9/2.wav");//  23
		list.add("audiofiles/9/3.wav");//  24
		
		
		list.add("audiofiles/10/1.wav");//  25 test 10
		list.add("audiofiles/10/2.wav");//  26
		list.add("audiofiles/10/3.wav");//  27
		 
		
		list.add("audiofiles/11/1.wav");//  28  test11
		list.add("audiofiles/11/2.wav");//  29
		list.add("audiofiles/11/3.wav");//  30
		 
		
		
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
