/**
 * 
 */
package windows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import objects.Beo;
import objects.FileOperator;
import objects.Igeny;
import objects.User;
import objects.dolgozik;

/**
 * @author jhorvath
 *
 */
public class CsereRendezo {

	private int honap;
	private int napokH;
	private int kezdo;
	private String expertM;
	private String expertCs;
	private String t1t2M;
	private String t1t2Cs;
	private String fix;
	private String spec;
	private String HVHT;
	private String HTHV;
	private ArrayList<User> multHonapHavi;
	private ArrayList<User> jelenHonapExpert;
	private ArrayList<User> jelenHonapT1T2;
	private ArrayList<Igeny> igenyekExpert;
	private ArrayList<Igeny> igenyekT1T2;
	private String multHonap;



	/**
	 * betölti a konfigot
	 */
	public void configBetoltes() {
		// TODO Auto-generated method stub
		
		//configBetoltes();
		try {
		
			BufferedReader br = new BufferedReader(new FileReader("series.conf"));
			
			honap = Integer.parseInt(br.readLine().split("=")[1]);
			
			napokH = Integer.parseInt(br.readLine().split("=")[1]);
			
			kezdo = Integer.parseInt(br.readLine().split("=")[1]);
			
			expertM = br.readLine().split("=")[1];
			
			expertCs = br.readLine().split("=")[1];
	
			t1t2M = br.readLine().split("=")[1];
			
			 t1t2Cs = br.readLine().split("=")[1];
			
			 fix = br.readLine().split("=")[1];
			
			 spec = br.readLine().split("=")[1];
			
			 HVHT = br.readLine().split("=")[1];
			 HTHV = br.readLine().split("=")[1];
			 
			 multHonap = br.readLine().split("=")[1];
			 
			 br.close();
		} catch (Exception ex) {}
		
			
		}
	
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	
	/***
	 * Visszatéríti az user helyét a múlthónapban
	 * @param us
	 * @return
	 */
	
	public int keresMultHonapban(User us) {
		
		for (int i = 0; i < multHonapHavi.size(); ++i) {
			if (us.getName().equals(multHonapHavi.get(i).getName())) {
				return i;
			}
		}
		
		return -1;
		
	}
	
	
	public void main() throws IOException {
		// TODO Auto-generated mehod stub

	    multHonapHavi = FileOperator.getPrewMonthsUser();
	    jelenHonapExpert = FileOperator.getInfoFromFile(1);   // expert beosztás
	    jelenHonapT1T2 = FileOperator.getInfoFromFile(2);     // t1t2 beosztás
	    
	    igenyekExpert = FileOperator.getIgenyek(1); // expert igények
	    igenyekT1T2 = FileOperator.getIgenyek(0);   //t1t2
	    
	  
	    // a multHonapot feltöltjük emberekkel
	 
	    Beo szabad = new Beo("semmi", dolgozik.szabadV);
	    int multHonapNapjai = multHonapHavi.get(1).getList().size();
	   // System.out.println(multHonapNapjai);
	    
	    int valaszto = 7;
	    
	    // expert
	    
	    for (int i = 1; i < jelenHonapExpert.size(); ++i) {
	    	
	    	//System.out.println(jelenHonapExpert.get(i).getList().size() + "izé");
	    	
	    	int p = keresMultHonapban(jelenHonapExpert.get(i));
	    	
	    	if (p != -1) {
	    		
	    		Beo beo1 = multHonapHavi.get(p).get(multHonapNapjai - 7);
	    		Beo beo2 = multHonapHavi.get(p).get(multHonapNapjai - 6);
	    		Beo beo3 = multHonapHavi.get(p).get(multHonapNapjai - 5);
	    		Beo beo4 = multHonapHavi.get(p).get(multHonapNapjai - 4);
	    		Beo beo5 = multHonapHavi.get(p).get(multHonapNapjai - 3);
	    		Beo beo6 = multHonapHavi.get(p).get(multHonapNapjai - 2);
	    		Beo beo7 = multHonapHavi.get(p).get(multHonapNapjai - 1);
	    		
	    		jelenHonapExpert.get(i).addBeokPrev(beo1, beo2, beo3, beo4, beo5, beo6, beo7);
	    		
	    	} else {
	    		Beo beo1 = szabad;
	    		Beo beo2 = szabad;
	    		Beo beo3 = szabad;
	    		Beo beo4 = szabad;
	    		Beo beo5 = szabad;
	    		Beo beo6 = szabad;
	    		Beo beo7 = szabad;
	    		
	    		jelenHonapExpert.get(i).addBeokPrev(beo1, beo2, beo3, beo4, beo5, beo6, beo7);
	    	}
	    	
	    	
	    	
	    	
	    	
	    	
	    }
	    
	    
	    
	    
	    for (int i = 1; i < jelenHonapT1T2.size(); ++i) {
	    	//System.out.println(i);
	    	//System.out.println(jelenHonapExpert.get(i).getList().size() + "izé");
	    	
	    	int p = keresMultHonapban(jelenHonapT1T2.get(i));
	    	
	    	if (p != -1) {
	    		//System.out.println(p);
	    		Beo beo1 = multHonapHavi.get(p).get(multHonapNapjai - 7);
	    		Beo beo2 = multHonapHavi.get(p).get(multHonapNapjai - 6);
	    		Beo beo3 = multHonapHavi.get(p).get(multHonapNapjai - 5);
	    		Beo beo4 = multHonapHavi.get(p).get(multHonapNapjai - 4);
	    		Beo beo5 = multHonapHavi.get(p).get(multHonapNapjai - 3);
	    		Beo beo6 = multHonapHavi.get(p).get(multHonapNapjai - 2);
	    		Beo beo7 = multHonapHavi.get(p).get(multHonapNapjai - 1);
	    		
	    		jelenHonapT1T2.get(i).addBeokPrev(beo1, beo2, beo3, beo4, beo5, beo6, beo7);
	    		
	    	} else {
	    		Beo beo1 = szabad;
	    		Beo beo2 = szabad;
	    		Beo beo3 = szabad;
	    		Beo beo4 = szabad;
	    		Beo beo5 = szabad;
	    		Beo beo6 = szabad;
	    		Beo beo7 = szabad;
	    		
	    		jelenHonapT1T2.get(i).addBeokPrev(beo1, beo2, beo3, beo4, beo5, beo6, beo7);
	    	}	
	    	
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	 
		
	}
	
	public static void main(String []args) {
		
		CsereRendezo v = new CsereRendezo();
		try {
			v.main();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
