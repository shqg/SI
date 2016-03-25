package audio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
	
	public static double distance(double[] y,double[]x){//x-testset    y-trainningSet
		double dis=0;
		for(int a=0;a<x.length;a++){
			dis +=Math.pow(Math.abs(x[a]-y[a]), 2);
		}
		dis=Math.pow(dis, 0.5);
		return dis;
	}
	
	public void vergleichen(List<List<double[]>> alleTrainList,List<List<double[]>> alleTestList){
		
		System.out.println("--------------------------------------"
				+ "-----------------------------------------------------"
				+ "-----------------------------------------------------");
 		System.out.printf("%14s %12s %12s %12s %12s %12s %12s %12s %12s %12s %12s%n",
		         "SpeakerA", "SpeakerB", "SpeakerC", "SpeakerD", "SpeakerE", "SpeakerF",
		         "SpeakerG", "SpeakerH", "SpeakerI", "SpeakerJ", "SpeakerK" );
		

		for(int a=0;a<alleTestList.size();a++){
			
			double dis=0; 
			
			int speakerA=0; int speakerB=0;int speakerC=0;int speakerD=0;
			int speakerE=0; int speakerF=0;int speakerG=0;int speakerH=0;
			int speakerJ=0; int speakerK=0;int speakerL=0;
			
			List<double[]> jedeTestWav=alleTestList.get(a);
			
			
		

			
			
			
			for(int b=0;b<jedeTestWav.size();b++){
				double mindistance1=Double.MAX_VALUE;
				double mindistance2=Double.MAX_VALUE;
				double mindistance3=Double.MAX_VALUE;
				double mindistance4=Double.MAX_VALUE;
				double mindistance5=Double.MAX_VALUE;
				double mindistance6=Double.MAX_VALUE;
				double mindistance7=Double.MAX_VALUE;
				double mindistance8=Double.MAX_VALUE;
				double mindistance9=Double.MAX_VALUE;
				double mindistance10=Double.MAX_VALUE;
				double mindistance11=Double.MAX_VALUE;
				
				//double[] jedeTestFrame=jedeTestWav.get(b);
				//TrainList 
//				for(int c=0;c<alleTrainList.size();c++){
				for(int c=1;c<=2;c++){
					List<double[]> jedeTrainWav=alleTrainList.get(c);
					for(int d=0;d<jedeTrainWav.size();d++){
						double[] jedeTrainFrame=jedeTrainWav.get(d);
						dis=Test.distance(jedeTestWav.get(b), jedeTrainFrame);
							//	System.out.println("jedetFrame=="+jedeTestFrame.length+"---"+jedeTrainFrame.length);
						if(dis<mindistance1){
							mindistance1=dis;
						}
						
					}
				} dis=0;
				
				for(int c=4; c<=5;c++){
					List<double[]> jedeTrainWav=alleTrainList.get(c);
					for(int d=0;d<jedeTrainWav.size();d++){
						double[] jedeTrainFrame=jedeTrainWav.get(d);
						dis=Test.distance(jedeTestWav.get(b), jedeTrainFrame);
						if(dis<mindistance2){
							mindistance2=dis;
						}
					}
				}dis=0;
				
				for(int c=7; c<=8;c++){
					List<double[]> jedeTrainWav=alleTrainList.get(c);
					for(int d=0;d<jedeTrainWav.size();d++){
						double[] jedeTrainFrame=jedeTrainWav.get(d);
						dis=Test.distance(jedeTestWav.get(b), jedeTrainFrame);
						if(dis<mindistance3){
							mindistance3=dis;
						}
					}
				}dis=0;
				
				for(int c=10; c<=11;c++){
					List<double[]> jedeTrainWav=alleTrainList.get(c);
					for(int d=0;d<jedeTrainWav.size();d++){
						double[] jedeTrainFrame=jedeTrainWav.get(d);
						dis=Test.distance(jedeTestWav.get(b), jedeTrainFrame);
						if(dis<mindistance4){
							mindistance4=dis;
						}
					}
				}dis=0;
				//speaker 5
				for(int c=13; c<=14;c++){
					List<double[]> jedeTrainWav=alleTrainList.get(c);
					for(int d=0;d<jedeTrainWav.size();d++){
						double[] jedeTrainFrame=jedeTrainWav.get(d);
						dis=Test.distance(jedeTestWav.get(b), jedeTrainFrame);
						if(dis<mindistance5){
							mindistance5=dis;
						}
					}
				}dis=0;
				
				//speaker 6
				for(int c=16; c<=16;c++){
					List<double[]> jedeTrainWav=alleTrainList.get(c);
					for(int d=0;d<jedeTrainWav.size();d++){
						double[] jedeTrainFrame=jedeTrainWav.get(d);
						dis=Test.distance(jedeTestWav.get(b), jedeTrainFrame);
						if(dis<mindistance6){
							mindistance6=dis;
						}
					}
				}dis=0;
				
				//speaker 7
				for(int c=18; c<=18;c++){
					List<double[]> jedeTrainWav=alleTrainList.get(c);
					for(int d=0;d<jedeTrainWav.size();d++){
						double[] jedeTrainFrame=jedeTrainWav.get(d);
						dis=Test.distance(jedeTestWav.get(b), jedeTrainFrame);
						if(dis<mindistance7){
							mindistance7=dis;
						}
					}
				}dis=0;
				
				//speaker 8
				for(int c=20; c<=21;c++){
					List<double[]> jedeTrainWav=alleTrainList.get(c);
					for(int d=0;d<jedeTrainWav.size();d++){
						double[] jedeTrainFrame=jedeTrainWav.get(d);
						dis=Test.distance(jedeTestWav.get(b), jedeTrainFrame);
						if(dis<mindistance8){
							mindistance8=dis;
						}
					}
				}dis=0;
				
				//speaker 9
				for(int c=23; c<=24;c++){
					List<double[]> jedeTrainWav=alleTrainList.get(c);
					for(int d=0;d<jedeTrainWav.size();d++){
						double[] jedeTrainFrame=jedeTrainWav.get(d);
						dis=Test.distance(jedeTestWav.get(b), jedeTrainFrame);
						if(dis<mindistance9){
							mindistance9=dis;
						}
					}
				}dis=0;
				
				//speaker 10
				for(int c=26; c<=27;c++){
					List<double[]> jedeTrainWav=alleTrainList.get(c);
					for(int d=0;d<jedeTrainWav.size();d++){
						double[] jedeTrainFrame=jedeTrainWav.get(d);
						dis=Test.distance(jedeTestWav.get(b), jedeTrainFrame);
						if(dis<mindistance10){
							mindistance10=dis;
						}
					}
				}dis=0;
				
				//speaker 11
				for(int c=29; c<=30;c++){
					List<double[]> jedeTrainWav=alleTrainList.get(c);
					for(int d=0;d<jedeTrainWav.size();d++){
						double[] jedeTrainFrame=jedeTrainWav.get(d);
						dis=Test.distance(jedeTestWav.get(b), jedeTrainFrame);
						if(dis<mindistance11){
							mindistance11=dis;
						}
					}
				}dis=0;
				
				double[] min = {mindistance1, mindistance2,mindistance3,
								mindistance4, mindistance5,mindistance6,
								mindistance7, mindistance8,mindistance9,
								mindistance10, mindistance11 };
		          Arrays.sort(min);
		          if(min[0]==mindistance1){
						speakerA++;
					}else if(min[0]==mindistance2){
						speakerB++;
					}else if(min[0]==mindistance3){
						speakerC++;
					}else if(min[0]==mindistance4){
						speakerD++;
					}else if(min[0]==mindistance5){
						speakerE++;
					}else if(min[0]==mindistance6){
						speakerF++;
					}else if(min[0]==mindistance7){
						speakerG++;
					}else if(min[0]==mindistance8){
						speakerH++;
					}else if(min[0]==mindistance9){
						speakerJ++;
					}else if(min[0]==mindistance10){
						speakerK++;
					}else if(min[0]==mindistance11){
						speakerL++;
					}
		         
				
			}
			float sum=speakerA+speakerB+speakerC+speakerD+speakerE+speakerF+speakerG
					+speakerH+speakerJ+speakerK+speakerL;
			float test1 =speakerA/sum*100; float test2 =speakerB/sum*100;float test3 =speakerC/sum*100;
			float test4 =speakerD/sum*100;float test5 =speakerE/sum*100;float test6 =speakerF/sum*100;
			float test7 =speakerG/sum*100;float test8 =speakerH/sum*100;float test9 =speakerJ/sum*100;
			float test10 =speakerK/sum*100;float test11 =speakerL/sum*100;
			
			
		            System.out.println();
		            System.out.printf("%-4d",a+1);
		            System.out.printf("%10f%%", test1);
		            System.out.printf("%12f%%", test2);
		            System.out.printf("%12f%%", test3);
		            System.out.printf("%12f%%", test4);
		            System.out.printf("%12f%%", test5);
		            System.out.printf("%12f%%", test6);
		            System.out.printf("%12f%%", test7);
		            System.out.printf("%12f%%", test8);
		            System.out.printf("%12f%%", test9);
		            System.out.printf("%12f%%", test10);
		            System.out.printf("%12f%%", test11); 
		            
		} 
	}
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AudioReader ar=new AudioReader();
		HammingWindow hw = new HammingWindow();
		List<List<double[]>> Frames_von_alleVedios = hw.hammingApply(ar.alleDateienFrames);//加窗后的所有帧
		
		LPC_Coefficient lc=new  LPC_Coefficient();
		lc.lpcfiles(Frames_von_alleVedios);		
		System.out.println("alle Wavfiles nach lpc = "+lc.lpc_lists.size());//alle wavfiles nach lpc
		
		
		
		 List<List<double[]>> alleTestList=new ArrayList<List<double[]>>(); //alleTestList
		 alleTestList.add(lc.lpc_lists.get(0));
		 alleTestList.add(lc.lpc_lists.get(3));
		 alleTestList.add(lc.lpc_lists.get(6));
		 alleTestList.add(lc.lpc_lists.get(9));
		 alleTestList.add(lc.lpc_lists.get(12));
		 alleTestList.add(lc.lpc_lists.get(15));
		 alleTestList.add(lc.lpc_lists.get(17));
		 alleTestList.add(lc.lpc_lists.get(19));
		 alleTestList.add(lc.lpc_lists.get(22));
		 alleTestList.add(lc.lpc_lists.get(25));
		 alleTestList.add(lc.lpc_lists.get(28));
		 
		CL_LPC clapply=new CL_LPC();// CompetitiveLernen
//		System.out.println("1");
		clapply.CL_LPC(lc.lpc_lists);
		
//		System.out.println("2");
		
		 System.out.println(" +++++++++++++LPC+++++++++++++++");
		Test ver=new Test();
		ver.vergleichen(clapply.kl_Lists,alleTestList );
		System.out.println();
		System.out.println("---------------------------------------"
				+ "-----------------------------------------------------"
				+ "-----------------------------------------------");
		
		 System.out.println();System.out.println();
		 System.out.println("+++++++++++++MFCC+++++++++++++++");
		 System.out.println();
		  
		  MFCC_Coefficient mc=new MFCC_Coefficient();
			 mc.mfcc(Frames_von_alleVedios);
//			 System.out.print("3");
			 
			 List<List<double[]>> alleTestList2=new ArrayList<List<double[]>>();
			 alleTestList2.add(mc.mfcc_lists.get(0));
			 alleTestList2.add(mc.mfcc_lists.get(3));
			 alleTestList2.add(mc.mfcc_lists.get(6));
			 alleTestList2.add(mc.mfcc_lists.get(9));
			 alleTestList2.add(mc.mfcc_lists.get(12));
			 alleTestList2.add(mc.mfcc_lists.get(15));
			 alleTestList2.add(mc.mfcc_lists.get(17));
			 alleTestList2.add(mc.mfcc_lists.get(19));
			 alleTestList2.add(mc.mfcc_lists.get(22));
			 alleTestList2.add(mc.mfcc_lists.get(25));
			 alleTestList2.add(mc.mfcc_lists.get(28));
			 
			 
			 CL_MFCC clmfcc=new CL_MFCC();
			 clmfcc.mfcc_apply(mc.mfcc_lists);
//			 System.out.println("4");
			 ver.vergleichen(clmfcc.kl_Lists, alleTestList2);
			 System.out.println();
				System.out.println("---------------------------------------"
						+ "-----------------------------------------------------"
						+ "--------------------------------------------------");
				 System.out.println(); System.out.println();
		
	}

}
