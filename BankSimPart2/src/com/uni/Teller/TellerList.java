/**
 * @author Jon Mirhadi
 * @author Neil Struth
 * 
 * @version 2.0
 * 
 * Stores a list of tellers
 * 
 */
package com.uni.Teller;

import java.util.ArrayList;

public class TellerList extends ArrayList<Teller>{
	
	/**
	 * Constructor for this teller list
	 */
	public TellerList(){
		super();
	}
	
	/**
	 * 
	 * Add a teller to the list
	 * @param t the teller to be added
	 */
	public void addTeller(Teller t){
		this.add(t);
	}

}
