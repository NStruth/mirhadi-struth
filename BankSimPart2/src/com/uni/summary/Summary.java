package com.uni.summary;

import com.uni.Teller.Teller;
import com.uni.account.Transaction;
import com.uni.account.Transaction.Choices;
import com.uni.customer.Customer;

public class Summary {
	private Customer c;
	private Teller t;
	private Transaction.Choices type;
	private Object amount;
	
		
	public Summary(Customer c, Teller t, Choices type, Object amount) {
		this.c = c;
		this.t = t;
		this.type = type;
		this.amount = amount;
	}


	public Customer getC() {
		return c;
	}


	public void setC(Customer c) {
		this.c = c;
	}


	public Teller getT() {
		return t;
	}


	public void setT(Teller t) {
		this.t = t;
	}


	public Transaction.Choices getType() {
		return type;
	}


	public void setType(Transaction.Choices type) {
		this.type = type;
	}


	public Integer getAmount() {
		return (Integer)amount;
	}


	public void setAmount(Object amount) {
		this.amount = amount;
	}
	
}
