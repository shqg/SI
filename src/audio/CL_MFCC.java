package audio;

import java.util.ArrayList;
import java.util.List;

public class CL_MFCC {
	
	public List<List<double[]>> kl_Lists;
	CompetitiveLearn Cl=new CompetitiveLearn();
	
	public void mfcc_apply(List<List<double[]>> mfcclists){
		
		int k=50;
		double l=0.05;
		this.kl_Lists=new ArrayList<List<double[]>>();
		List<double[]> KL;
		
		for(int i=0;i<mfcclists.size();i++){
			KL=Cl.Kom_lerenen(mfcclists.get(i),k, l);
			  kl_Lists.add(KL);
		}
		
	}

}
