package objects;

import java.util.ArrayList;

public class Igeny {
	
	private String name;
	private User user;
	private int nap;
	private ArrayList<Integer> adnaErte;
	
	private IgenyTipus tipus;
	
	
	public Igeny() {
		
		adnaErte = new ArrayList<Integer>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNap() {
		return nap;
	}

	public void setNap(int nap) {
		this.nap = nap;
	}

	public Integer getAdnaErte(int i) {
		return adnaErte.get(i);
	}

	public void addToAdnaErte(Integer nap) {
		adnaErte.add(nap);
	}
	
	public int lengthOfAdnaErte() {
		return adnaErte.size();
	}

	public IgenyTipus getTipus() {
		return tipus;
	}

	public void setTipus(IgenyTipus tipus) {
		this.tipus = tipus;
	}
	
	public String toString() {
		
		String st = this.name;
		
		return st;
		
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		// TODO Auto-generated method stub
		this.user = user;
		
	}
}
