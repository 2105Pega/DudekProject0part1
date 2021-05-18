package com.peter;

import java.util.ArrayList;

public class Account {

	private String name;
	
	private String uuid;
	
	private Customer owner; 
	
	private ArrayList<Transaction> transactions;
	

	public Account(String name, Customer owner, Bank bankA) {
		
		this.name = name;
		this.owner = owner;
		

		this.uuid = bankA.getNewAccountUUID();
		
		this.transactions = new ArrayList<Transaction>();
		
	}

	

	public String getSummary() {

		double balance = this.getBalance();
		

		if(balance >= 0) {
			return String.format("%s Account# %s: $%.02f", this.name, this.uuid, balance);
		} else {
			return String.format("%s Account# %s: -$%.02f", this.name, this.uuid, balance);
		}
	}
	
	public double getBalance() {
		double balance = 0;
		for(Transaction t : this.transactions) {
			balance += t.getAmount();
		}
		
		return balance;
	}
	
	

	public void addTransaction(double amount) {
		
		Transaction newTrans = new Transaction(amount, this);
		this.transactions.add(newTrans);
	}
	
	
	public String getUUID() {
		return this.uuid;
	}

	
}
