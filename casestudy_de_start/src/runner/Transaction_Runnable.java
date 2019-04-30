package runner;

import java.util.List;
import java.util.Scanner;

import dao.TransactionDao;
import model.Transaction;

public class Transaction_Runnable extends Main {
	// connect to transactionDAO and use input to get count of transaction type
	public void getTotalByType() throws Exception {	
		System.out.println("Please enter transaction Type:");
		String type = s.next();
		TransactionDao td = new TransactionDao();
		Transaction mytransaction = td.gettotalbyType(type);
		System.out.println(mytransaction.getCount());
	}
	
	public void displayZipMonthYear() {
		// connect to transactionDAO and use dates to find transactions 
		try {
			System.out.println("Please enter zip code: ");
			String zip = s.next();
			if (zip.length() != 5) {
				throw new Exception(); 
			}
			System.out.println("Please enter month: ");
			int month = s.nextInt();
			if( month < 1 || month > 12) {
				throw new Exception(); 
			}
			System.out.println("Please enter year: ");
			int year = s.nextInt();
			String yearToString = Integer.toString(year); 
			if (yearToString.length() != 4) {
				throw new Exception(); 
			}

			// Store in list, multiple records 
			TransactionDao td = new TransactionDao();
			List<Transaction> tl = td.displayByZipMonthYear(zip,month,year);
			if(!tl.isEmpty()) {
				String formatter = "\t\t\t\t\t %5s %10s %10s %15s %15s %20s %10s %n"; 
				System.out.printf(formatter, "Day", "Month", "Year", "SSN", "Branch #", "Type", "Value");
				System.out.println("\t\t\t\t\t -----------------------------------------------------------------------------------------------");
				for(Transaction t: tl){
					System.out.format(formatter,t.getDay(), t.getMonth(), t.getYear(), t.getSsn(), t.getBranchCode(), t.getType(), String.format("%.2f", t.getValue()));
					System.out.println();
				}
			}
			else {
				throw new Exception();
			}
		}
		catch(Exception e) {
			System.out.println("No records to display");
		}
	}
	
	public void transactionByType() {
		// connect to transactionDAO and use input to get count and total of transaction type
		try {
			System.out.println("Please Enter The Transaction Type:");
			String type = s.next();

			TransactionDao td = new TransactionDao();
			Transaction mytransaction = td.transactionByType(type);
			if(!mytransaction.equals(null)) {
				System.out.println("\t\t\t\t\t Number of Transactions: " + mytransaction.getCount());
				System.out.format("\t\t\t\t\t Sum of Total Transactions: $%.2f",mytransaction.getValue()); 
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("No records to display");
		}
	}

	public void transactionByState() {	
		// connect to transactionDAO and use input to get count of transactions in given state
		try {
			System.out.println("Please enter State Abbreviation:");

			String state = s.next();
			if(state.length() != 2) {
				throw new Exception();
			}

			TransactionDao td = new TransactionDao();
			Transaction mytransaction = td.transactionByState(state);
			System.out.println("\t\t\t\t\t Sum of Total Transactions: $" + String.format("%.2f", mytransaction.getValue()));
			System.out.println("\t\t\t\t\t Number of Transactions: " + mytransaction.getCount());
		}
		catch(Exception e) {
			System.out.println("Not Valid State");
		}

	}

}


	




	

