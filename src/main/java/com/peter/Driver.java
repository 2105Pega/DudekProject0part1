package com.peter;

//import java.io.IOException;
import java.util.Scanner;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


  
public class Driver {
	 
	
	
	static Logger log = LogManager.getLogger("Bank");
//	private static final Logger LOGGER = Logger.getLogger(MyClass.class.getName());
//	 Handler handlerObj = new ConsoleHandler();
//	 handlerObj.setLevel(Level.ALL);
//	 log.addHandler(handlerObj);
//	 log.setLevel(Level.ALL);
//	 log.setUseParentHandlers(false);

	public static void main(String[] args) {
		
		log.info("Welcome to Bank of America");

		Scanner sc = new Scanner(System.in);

		Bank bankA = new Bank("Bank of America");

		Customer aCustomer = bankA.addCustomer("John123", "123");

		Account newAccount = new Account("Saving", aCustomer, bankA);
		aCustomer.addAccount(newAccount);
		bankA.addAccount(newAccount);

		Customer curCustomer;
		while (true) {

			curCustomer = Driver.mainMenuPrompt(bankA, sc);


			Driver.printCustomerMenu(curCustomer, sc);

		}
	}

	public static Customer mainMenuPrompt(Bank bankA, Scanner sc) {

		String customerID;
		String pin;
		Customer authCustomer;

		do {

			System.out.printf("\n\nWelcome to %s\n\n", bankA.getName());
			System.out.println("Enter your customer ID (above): ");
			customerID = sc.nextLine();
			System.out.print("Enter password: ");
			pin = sc.nextLine();

			authCustomer = bankA.customerLogin(customerID, pin);

			if (authCustomer == null) {
				System.out.println("Incorrect customer name or password. Try again.");
			}

		} while (authCustomer == null); 

		return authCustomer;

	}

	public static void printCustomerMenu(Customer theCustomer, Scanner sc) {


		theCustomer.printAccountsSummary();

		int choice;


		do {
			System.out.printf("%s, how can we serve you today?\n", theCustomer.getFirstName());
			System.out.println(" 1) Deposit");
			System.out.println(" 2) Withdraw");
			System.out.println(" 3) Transfer");
			System.out.println(" 4) Exit");
			System.out.println();
			System.out.println("Enter choice 1-4: ");
			choice = sc.nextInt();

			if (choice < 1 || choice > 4) {
				System.out.println("Invalid choice. Please enter number 1-4.");
			}
		} while (choice < 1 || choice > 4);


		switch (choice) {
		case 1:
			Driver.depositFunds(theCustomer, sc);
			break;
		case 2:
			Driver.withdrawFunds(theCustomer, sc);
			break;
		case 3:
			Driver.transferFunds(theCustomer, sc);
			break;
		case 4:
			sc.nextLine();
			break;
		}

		if (choice != 4) {
			Driver.printCustomerMenu(theCustomer, sc);
		}

	}
	


	public static void depositFunds(Customer theCustomer, Scanner sc) {
		
		int toAcct;
		double amount;
		double acctBal;
		

		 do {
			 System.out.printf("Enter the number (1-%d) of the account to deposit to:\n",
					 theCustomer.numAccounts());
			 toAcct = sc.nextInt()-1;
			 if(toAcct < 0 || toAcct >= theCustomer.numAccounts()) {
				 System.out.println("Invalid account. Please try again");
			 }
			 
		 } while(toAcct < 0 || toAcct >= theCustomer.numAccounts());
		acctBal = theCustomer.getAcctBalance(toAcct);
		

		 do {
			 System.out.println("Enter the amount to deposit:");
			 amount = sc.nextDouble();
			 if(amount<0) {
				 System.out.println("Amount must be greater than zero.");
			 } 

			 } while(amount < 0);
		 

		 sc.nextLine();
		 

		 theCustomer.addAcctTransaction(toAcct, amount);
		 log.info(theCustomer.getFirstName() + " deposited $" + amount + " to the account# " + theCustomer.getAcctUUID(toAcct));
		
	}
	

	public static void withdrawFunds(Customer theCustomer, Scanner sc) {
		

				int fromAcct;
				double amount;
				double acctBal;
				

				 do {
					 System.out.printf("Enter the number (1-%d) of the account to withdraw from:\n", theCustomer.numAccounts());
					 fromAcct = sc.nextInt()-1;
					 if(fromAcct < 0 || fromAcct >= theCustomer.numAccounts()) {
						 System.out.println("Invalid account. Please try again");
					 }
					 
				 } while(fromAcct < 0 || fromAcct >= theCustomer.numAccounts());
				acctBal = theCustomer.getAcctBalance(fromAcct);
				

				 do {
					 System.out.println("Enter the amount to withdraw:");
					 amount = sc.nextDouble();
					 if(amount<0) {
						 System.out.println("Amount must be greater than zero.");
					 } else if (amount > acctBal) {
						 System.out.printf("Amount must not exceed the balance of $%.02f.\n", acctBal);
					 }
					 
					 } while(amount < 0 || amount > acctBal);
				 
				 sc.nextLine();

					 

				 theCustomer.addAcctTransaction(fromAcct, -1*amount);
				 log.info(theCustomer.getFirstName() + " withdrew $" + amount + " from the account# " + theCustomer.getAcctUUID(fromAcct));
	}
	

		public static void transferFunds(Customer theCustomer, Scanner sc) {
			
			int fromAcct;
			int toAcct;
			double amount;
			double acctBal;
			

			 do {
				 System.out.printf("Enter the number (1-%d) of the account to transfer from: ", theCustomer.numAccounts());
				 fromAcct = sc.nextInt()-1;
				 if(fromAcct < 0 || fromAcct >= theCustomer.numAccounts()) {
					 System.out.println("Invalid account. Please try again");
				 }
				 
			 } while(fromAcct < 0 || fromAcct >= theCustomer.numAccounts());
			acctBal = theCustomer.getAcctBalance(fromAcct);
			

			 do {
				 System.out.printf("Enter the number (1-%d) of the account to transfer to: ", theCustomer.numAccounts());
				 toAcct = sc.nextInt()-1;
				 if(toAcct < 0 || toAcct >= theCustomer.numAccounts()) {
					 System.out.println("Invalid account. Please try again");
				 }
				 
			 } while(toAcct < 0 || toAcct >= theCustomer.numAccounts());
			 

			 do {
				 System.out.println("Enter the amount to transfer:");
				 amount = sc.nextDouble();
				 if(amount < 0) {
					 System.out.println("Amount must be greater than zero.");
				 } else if (amount > acctBal) {
					 System.out.printf("Amount must not exceed the balance of $%.02f.\n", acctBal);
				 }
				 
				 } while(amount < 0 || amount > acctBal);
			 

			 theCustomer.addAcctTransaction(fromAcct, -1*amount);
			 

			 theCustomer.addAcctTransaction(toAcct, amount);
			 
			 log.info(theCustomer.getFirstName() + " transfered $" + amount + " from the account# " + theCustomer.getAcctUUID(fromAcct) + " to the account# " + theCustomer.getAcctUUID(toAcct));
			 }

	
}

		
