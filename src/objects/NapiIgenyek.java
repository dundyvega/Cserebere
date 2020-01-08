package objects;

import java.util.ArrayList;

public class NapiIgenyek {
	
	
	ArrayList<Igeny> napiIgenyek;
	
	public NapiIgenyek() {
		
		napiIgenyek = new ArrayList<Igeny>();
	}

	public Igeny getNapiIgenyek(int i) {
		return napiIgenyek.get(i);
	}
	
	public int getLengthOfNap() {
		
		return napiIgenyek.size();
	}

	public void addNapiIgenyek(Igeny ig) {
		napiIgenyek.add(ig);
	}

}
