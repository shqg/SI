package audio;

import java.util.ArrayList;
import java.util.List;

public class CL_LPC {
	
	public List<List<double[]>> kl_Lists;
	CompetitiveLearn Cl=new CompetitiveLearn();
	    
   public void CL_LPC(List<List<double[]>> lpclist){	 
		 this.kl_Lists=new ArrayList<List<double[]>>();
		 List<double[]> KL;
		 int k=200;		 
		 double l=0.05;	
		 
		 for (int i = 0; i < lpclist.size(); i++) {			
			 KL = Cl.Kom_lerenen(lpclist.get(i), k,l);
	            kl_Lists.add(KL);       
	        }		 
	 }
}

 