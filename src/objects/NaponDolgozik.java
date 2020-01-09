package objects;

import java.util.ArrayList;

public class NaponDolgozik {
	
	private int nap;
	private ArrayList<User> nevek;
	private ArrayList<Integer> ertek;
	
	public NaponDolgozik(int n) {
		
		nap = n;
		nevek = new ArrayList<User>();
		ertek = new ArrayList<Integer>();
		
	}
	
	public void setErtek(int ert) {
		ertek.add(ert);
	}
	
	public int getErtek(int i) {
		return ertek.get(i);
	}
	
	
	public int getNap() {
		return nap;
	}
	public void setNap(int nap) {
		this.nap = nap;
	}
	
	public String getName(int i) {
		
		return nevek.get(i).getName();
	}
	
	public User getUser(int i) {
		return nevek.get(i);
	}
	
	public void addUser(User us) {
		nevek.add(us);
	}
	
	public int getLength() {
		return nevek.size();
	}

	
	/**
	 * A nevek között megkeresi a nameS nevet, és az indexén beállítja az indexet
	 * @param nameS
	 * @param ertek
	 */
	public void setErtekNevAlapjan(String nameS, int ertekS) {
		// TODO Auto-generated method stub
		
		boolean megtalalt = false;
		
		for (int i = 0; i < nevek.size() && !megtalalt; ++i) {
			
			if (nameS.equals(nevek.get(i).getName())) {
				megtalalt = true;
				ertek.set(i, ertekS);
				//System.out.println(nevek.get(i).getName());
				
			}
		}
		
	}
	
	

}
