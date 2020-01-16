package objects;

import java.util.ArrayList;

public class NaponDolgozik {
	
	private int nap;
	private ArrayList<User> nevek;
	private ArrayList<Integer> ertek;
	private int eztAnapotCsereli;
	
	public int getEztAnapotCsereli() {
		return eztAnapotCsereli;
	}

	public void setEztAnapotCsereli(int eztAnapotCsereli) {
		this.eztAnapotCsereli = eztAnapotCsereli;
	}

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
				//System.out.println(nevek.get(i).getName() + "psztpaszta" + ertekS);
				
			}
		}
		
	}

	public void setCsere(int cser) {
		// TODO Auto-generated method stub
		
		this.eztAnapotCsereli = cser;
		
	}

	public boolean benneVan(User user) {
		// TODO Auto-generated method stub
		return nevek.contains(user);
	}

	public void remove(String name) {
		// TODO Auto-generated method stub
		
		int i = 0;
		boolean talalt = false;
		
		while (i < nevek.size()) {
			
			if (nevek.get(i).getName().equals(name)) {
				nevek.remove(i);
				//System.out.println(this.getNap() + "töröltük " + name);
				talalt = false;
			}
			
			i++;
			
			
		}
		
	}
	
	/**
	 * 
	 * 
	 * @param day
	 * @return
	 * @dundyvega
	 * 
	 * Visszatéríti a két napon dolgozók metszetét
	 * 
	 */
	public ArrayList<User> segment(NaponDolgozik day) {
		
		ArrayList<User> returner = new ArrayList<User>();
		
		for (int i = 0; i < nevek.size(); ++i) {
			for (int j = 0; j < day.getLength(); ++j) {
				
				if (nevek.get(i).equals(day.getUser(j))) {
					
					returner.add(nevek.get(i));
					
				}
			}
			
		}
		
		
		return returner;
	}

	public int getUserID(String name) {
		// TODO Auto-generated method stub
		
		boolean nemTalalt = false;
		int t = -1;
		for (int i = 0; i < nevek.size() && !nemTalalt; ++i) {
			if (nevek.get(i).getName().equals(name)) {
				nemTalalt = true;
				t = i;
			}
			
		}
		
		return t;
	}
	
	

}
