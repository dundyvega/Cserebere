package objects;

import java.util.ArrayList;

public class CsiribiriExpertKetHonap {
	
	private ArrayList<Nap> napok;
	private int honapValaszto;
	
	public int getSize() {
		return napok.size();
	}
	
	public void addNap(Nap nap) {
		napok.add(nap);
	}

	public int getHonapValaszto() {
		return honapValaszto;
	}

	public void setHonapValaszto(int honapValaszto) {
		this.honapValaszto = honapValaszto;
	}
	

}
