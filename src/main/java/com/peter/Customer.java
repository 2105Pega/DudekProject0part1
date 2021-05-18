package com.peter;

//import java.io.Serializable;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Customer {

	private String firstName;
	
	private String uuid;

	private byte pinHash[];
	

	private ArrayList<Account> accounts;

	
	
	public Customer(String firstName, String pin, Bank bankA) {
		

		this.firstName = firstName;

		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			this.pinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error, NoSuchAlgorithmException.");
			e.printStackTrace();

			System.exit(1);
		}
		

		this.uuid = bankA.getNewCustomerUUID();
		

		this.accounts = new ArrayList<Account>();
		

		System.out.printf("New customer %s with customer ID %s created. \n", firstName, this.uuid);
		
		
	}
	

	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}

	

	public boolean validatePin(String aPin) {
		
		 try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(aPin.getBytes()),
					this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			
			System.out.println("Error, NoSuchAlgorithmException");
			e.printStackTrace();

			System.exit(1);
		}
		 return false; 
		
	}
	

	public String getUUID() {
		return this.uuid;
	}
	

	public String getFirstName() {
		return this.firstName;
	}
	
	
	public void printAccountsSummary() {
		System.out.printf("\n\n%s's accounts:\n", this.firstName);
		for(int a = 0; a < this.accounts.size(); a++) {
			System.out.printf("  %d) %s\n", a+1,
					this.accounts.get(a).getSummary());
		}
		System.out.println();
	}
	
	

	public int numAccounts() {
		return this.accounts.size();
	}
	
	

	public double getAcctBalance(int acctIdx) {
		return this.accounts.get(acctIdx).getBalance();
	}
	
	
	public String getAcctUUID(int acctIdx) {
		return this.accounts.get(acctIdx).getUUID();
	}
	
	public void addAcctTransaction(int acctIdx, double amount) {
		this.accounts.get(acctIdx).addTransaction(amount);
	}
	
	
}
