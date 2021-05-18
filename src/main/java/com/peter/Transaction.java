package com.peter;


public class Transaction {

	private double amount;
	
	private Account inAccount;
	
	public Transaction(double amount, Account inAccount){		
		this.amount = amount;
		this.inAccount = inAccount;
	}
	

	public double getAmount() {
		return this.amount;
	}
	
}
