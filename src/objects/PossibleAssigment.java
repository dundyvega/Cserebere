package objects;

public class PossibleAssigment {
	
	private String ki;
	private String kivel;
	private int mit;
	private int mivel;
	private int ertek;
	private IgenyTipus csT;
	
	public IgenyTipus getCsT() {
		return csT;
	}

	public void setCsT(IgenyTipus csT) {
		this.csT = csT;
	}

	public PossibleAssigment(String ki, String kivel, int mit, int mivel) {
		
		this.ki = ki;
		this.kivel = kivel;
		this.mit = mit;
		this.mivel = mivel;
	}
	
	public String getKi() {
		return ki;
	}
	public void setKi(String ki) {
		this.ki = ki;
	}
	public String getKivel() {
		return kivel;
	}
	public void setKivel(String kivel) {
		this.kivel = kivel;
	}
	public int getMit() {
		return mit;
	}
	public void setMit(int mit) {
		this.mit = mit;
	}
	public int getMivel() {
		return mivel;
	}
	public void setMivel(int mivel) {
		this.mivel = mivel;
	}
	public int getErtek() {
		return ertek;
	}
	public void setErtek(int ertek) {
		this.ertek = ertek;
	}
	
	

}
