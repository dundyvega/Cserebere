package objects;

public class ModifiedUser {
	
	private User user;
	private int ertek;
	private int nap;
	private dolgozik dolg;
	private int eztAnapot;
	private boolean lehetetlen = false;
	
	public ModifiedUser(User user, int ertek, int nap, dolgozik dolg, int eztANapot) {
		
		this.nap = nap;
		this.user = user;
		this.ertek = ertek;
		this.dolg = dolg;
		
		this.setEztAnapot(eztANapot);
	}
	
	public String toString() {
		
		return user + " adja: " + nap;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getErtek() {
		return ertek;
	}
	public void setErtek(int ertek) {
		this.ertek = ertek;
	}
	public int getNap() {
		return nap;
	}
	public void setNap(int nap) {
		this.nap = nap;
	}

	public dolgozik getDolg() {
		return dolg;
	}

	public void setDolg(dolgozik dolg) {
		this.dolg = dolg;
	}

	public int getEztAnapot() {
		return eztAnapot;
	}

	public void setEztAnapot(int eztAnapot) {
		this.eztAnapot = eztAnapot;
	}

	public void setLehetetlenites(boolean b) {
		// TODO Auto-generated method stub
		this.setLehetetlen(true);
		
	}

	public boolean isLehetetlen() {
		return lehetetlen;
	}

	public void setLehetetlen(boolean lehetetlen) {
		this.lehetetlen = lehetetlen;
	}

}
