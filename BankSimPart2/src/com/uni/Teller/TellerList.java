package com.uni.Teller;

import java.util.ArrayList;

public class TellerList extends ArrayList<Teller>{
	
	public TellerList(){
		super();
	}
	
	public void addTeller(Teller t){
		this.add(t);
	}
	
	public void openTeller(int index){
		//TODO
		//this.get(index).open();
	}
	
	public void closeTeller(int index){
		//TODO
		//this.get(index).close();
	}

}
