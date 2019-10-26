package objects;

import java.util.ArrayList;

public class User {

	private String name;
	
	private ArrayList<Beo> list = new ArrayList<Beo>();

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
	
}
