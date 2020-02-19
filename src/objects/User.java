package objects;

import java.util.ArrayList;

public class User {

	private String name;
	
	private ArrayList<Beo> list = new ArrayList<Beo>();

	private String cser = "";

	public String getName() {
		return name;
	}
	
	public User(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Beo> getList() {
		return list;
	}

	public void setList(ArrayList<Beo> list) {
		this.list = list;
	}
	
	public void addToList(Beo b) {
		
		list.add(b);
		
	}
	
	public void remove(Beo b) {
		
		list.remove(b);

	}
	
	public Beo get(int i) {
		
		return list.get(i);
	}
	
	public String toString() {
		return name;
	}

	public void setEztAnapotAdnaErte(int cser) {
		// TODO Auto-generated method stub
		this.cser = this.cser += cser + ",";
		
	}

	public String getCser() {
		
		return cser;
	}

	public void setCser(int cser) {
		this.cser = this.cser += cser + ",";
	}

	public void addBeokPrev(Beo beo1, Beo beo2, Beo beo3, Beo beo4, Beo beo5, Beo beo6, Beo beo7) {
		// TODO Auto-generated method stub
		list.add(0, beo7);
		list.add(0, beo6);
		list.add(0, beo5);
		list.add(0, beo4);
		list.add(0, beo3);
		list.add(0, beo2);
		list.add(0, beo1);
		
	}
	
}
