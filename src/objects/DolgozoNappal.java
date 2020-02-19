package objects;

public class DolgozoNappal {
	
	private String name;
	private String mikor = "";
	boolean specNap = false;
	private String napNeve = "";
	
	public DolgozoNappal(String name, String mikor, String napNeve, boolean specNap) {
	
		this.name = name;
		this.mikor = mikor;
		this.napNeve = napNeve;
		this.specNap = specNap;
		
	}
	
	public String getNapNeve() {
		return napNeve;
	}
	public void setNapNeve(String napNeve) {
		this.napNeve = napNeve;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMikor() {
		return mikor;
	}
	public void setMikor(String mikor) {
		this.mikor = mikor;
	}
	public boolean isSpecNap() {
		return specNap;
	}
	public void setSpecNap(boolean specNap) {
		this.specNap = specNap;
	}

}
