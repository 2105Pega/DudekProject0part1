package com.peter;

import java.util.UUID;
import java.util.ArrayList;

public class Bank {

	private String name;
	
	private ArrayList<Customer> customers;
	
	private ArrayList<Account> accounts;
	

	public Bank(String name) {
		this.name = name;
		this.customers = new ArrayList<Customer>();
		this.accounts = new ArrayList<Account>();
	}
	
	

	public String getNewCustomerUUID() {
		
		String custID;

		UUID uuid = UUID.randomUUID();

		custID = uuid.toString().substring(0, 8);
		
		return custID;  
	}
	
	

	public String getNewAccountUUID() {
		String accID;

		UUID uuid = UUID.randomUUID();

		accID = uuid.toString();
		
		return accID;  
	}
	
	
	

	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}
	
	

	public Customer addCustomer(String firstName, String pin) {
		

		Customer newCustomer = new Customer(firstName, pin, this);
		this.customers.add(newCustomer);

		Account newAccount = new Account("Checking", newCustomer, this);

		newCustomer.addAccount(newAccount);
		this.accounts.add(newAccount);
		
		return newCustomer;
	}
	
	

	public Customer customerLogin(String customerID, String pin) {
		

		for( Customer u : this.customers) {
			

			if(u.getUUID().compareTo(customerID) == 0 && u.validatePin(pin)) {
				return u;
			}
		}
		

		return null;
		
	}
	

	public String getName() {
		return this.name;
	}
	
	
}
 