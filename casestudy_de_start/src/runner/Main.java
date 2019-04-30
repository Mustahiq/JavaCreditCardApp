package runner;

import java.util.Scanner;

public class Main  {

	public static Scanner s = new Scanner(System.in);		

	public static void main(String[] args) throws Exception {
		Transaction_Runnable t = new Transaction_Runnable();
		Customer_Runnable c = new Customer_Runnable();  
		int choice = 0; 
		while(choice != 8) {

			mainMenu(); 
			System.out.println("Select your choice: "); 	
			try {
				choice = s.nextInt();
				if(choice < 1 || choice > 8) {
					throw new Exception(); 
				}
			}
			catch(Exception e) {
				System.out.println("Choose between 1-8");
				s.nextLine(); 
			}

			switch(choice) {
			case 1:{
				t.displayZipMonthYear();
				break; 
			}
			case 2:{
				t.transactionByType();
				break;
			}
			case 3:{
				t.transactionByState();
				break;
			}
			case 4:{
				c.displayAccount();
				break;
			}
			case 5:{
				c.updateAccountInfo();
				break;
			}
			case 6:{
				c.viewMonthlyBill();
				break;
			}
			case 7:{
				c.displayBetweenDates();
				break;
			}
			case 8:{
				System.out.println("Program Terminated");
				break;
			}
			}
		}
		s.close();


	}



	public static void mainMenu() {
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t Credit Card System ");
		System.out.println("\t\t\t\t\t ----------------------------------------------------------------------------"); 
		System.out.println("\t\t\t\t\t 1. Display transactions by zipcode"); 
		System.out.println("\t\t\t\t\t 2. Display total of transactions by type");
		System.out.println("\t\t\t\t\t 3. Display total of transactions by State");
		System.out.println("\t\t\t\t\t 4. Display customer account details");
		System.out.println("\t\t\t\t\t 5. Update customer account");
		System.out.println("\t\t\t\t\t 6. Display monthly bill"); 
		System.out.println("\t\t\t\t\t 7. Display transactions between dates");
		System.out.println("\t\t\t\t\t 8. Exit Program");

	}
}
