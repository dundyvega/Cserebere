package objects;

public class Beo {
	
	private String leiras;
	
	
	private dolgozik userke;


	public Beo(String leiras) {
		
		this.leiras = leiras;
		
	}
	
	public Beo(String leiras, dolgozik userke) {
		
		this.leiras = leiras;
		this.userke = userke;
	}
	
	public String getLeiras() {
		return leiras;
	}


	public void setLeiras(String leiras) {
		this.leiras = leiras;
	}


	public dolgozik getUserke() {
		return userke;
	}


	public void setUserke(dolgozik userke) {
		this.userke = userke;
	}
	
	
	
	
	

}
