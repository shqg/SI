package audio;

import java.util.ArrayList;
import java.util.List;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		AudioReader ar=new AudioReader();
		HammingWindow hw = new HammingWindow();
		List<List<double[]>> Frames_von_alleVedios = hw.hammingApply(ar.alleDateienFrames);//加窗后的所有帧
		
		 
		 System.out.println();
		 System.out.println("+++++++++++++MFCC+++++++++++++++");
		 System.out.println();
		  
		  MFCC_Coefficient mc=new MFCC_Coefficient();
			 mc.mfcc(Frames_von_alleVedios);
			 System.out.println("3");
			 
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
			 System.out.println("4");
			 Test te=new Test();
			 te.vergleichen(clmfcc.kl_Lists, alleTestList2);
			 
	}

}
