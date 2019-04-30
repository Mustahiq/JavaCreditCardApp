package runner;

import java.util.List;
import dao.CustomerDao;
import model.Customer;
import resources.myQuries;


public class Customer_Runnable extends Main {
	
		private String[] columns = {"","FIRST_NAME","MIDDLE_NAME", "LAST_NAME", "STREET_NAME", "APT_NO", "CUST_CITY", "CUST_STATE", "CUST_COUNTRY", "CUST_ZIP", "CUST_PHONE", "CUST_EMAIL",""};
	
		public void displayAccount() { 
			try {
				System.out.println("Please Enter Card Number:");
				String cardNum = s.next();
				if(cardNum.length() != 16) {
					throw new Exception();
				}
				System.out.println("Enter Social Security Number "); 
				String ssn = s.next(); 
				if(ssn.length() != 9) {
					throw new Exception(); 
				}

				CustomerDao cd = new CustomerDao(); 
				Customer mc = cd.displayAccount(cardNum,ssn); 
				if(!mc.equals(null)) {
					String formatter = "\n %-10s %-11s %-10s %-10s %-20s %-12s %-8s %-10s %-8s %-15s %-8s %-10s %-20s\n"; 
					System.out.printf(formatter, "First Name", "Middle Name", "Last Name", "SSN", "Card #", 
							"Street", "Apt #", "City", "State", "Country", "Zip", "Phone #", "Email Address \n");
					System.out.format(formatter,
							mc.getFirstName(), mc.getMiddleName(), mc.getLastName(), mc.getSsn(), mc.getCardNo(), mc.getStreetName(), mc.getAptNo(), mc.getCustomerCity(),
							mc.getCustomerState(), mc.getCustomerCountry(), mc.getCustomerZip(), mc.getCustomerPhone(), mc.getCustomerEmail());  
				}
			} 
			catch(Exception e) {
				System.out.println("No Records To Display"); 
			} 
		}
	
		public void updateAccountInfo() throws Exception {
			try {	
				System.out.println("Please Enter Card Number"); 
				String cardNum = s.next(); 
				if(cardNum.length() != 16) {
					throw new Exception();
				}

				System.out.println("Enter Social Security Number "); 
				String ssn = s.next(); 
				if(ssn.length() != 9) {
					throw new Exception(); 
				}

				CustomerDao cd = new CustomerDao(); 
				Customer mc = cd.displayAccount(cardNum,ssn);
				if(!mc.equals(null)) {
					System.out.format("\n %10s %15s %10s %16s %8s %15s %8s %15s %8s %10s %20s \n", "First Name", "Middle Name", "Last Name", 
							"Street", "Apt #", "City", "State", "Country", "Zip", "Phone #", "Email Address");
					System.out.format("\n %10s %15s %10s %16s %8s %15s %8s %15s %8s %10s %20s \n",
							mc.getFirstName(), mc.getMiddleName(), mc.getLastName(), mc.getStreetName(), mc.getAptNo(), mc.getCustomerCity(),
							mc.getCustomerState(), mc.getCustomerCountry(), mc.getCustomerZip(), mc.getCustomerPhone(), mc.getCustomerEmail());
				}
				int choice = 0; 
				while (choice != 12) {
					menu(); 
					CustomerDao cdao = new CustomerDao();
					choice = s.nextInt();
					String columnName=columns[choice];
					String change= "";

					switch (choice) {
					case 1:
						//change the first name
						System.out.println("Enter First Name");
						change = s.next();
						break;
					case 2:
						System.out.println("Enter Middle Name");
						change = s.next();			
						break;
					case 3:
						System.out.println("Enter Last Name");
						change = s.next();
						break;
					case 4:
						System.out.println("Enter Street Name");
						change = s.next();
						break;
					case 5:
						System.out.println("Enter Apartment Number");
						change = s.next();	
						break;
					case 6:
						System.out.println("Enter City Name");
						change = s.next();
						break;
					case 7:
						System.out.println("Enter State Name");
						change = s.next();
						break;
					case 8: 
						System.out.println("Enter Country Name");
						change = s.next();
						break; 
					case 9: 
						System.out.println("Enter Zip Code");
						change = s.next();
						break; 
					case 10: 
						System.out.println("Enter Phone Number"); 
						change =s.next();
					case 11:
						System.out.println("Enter Email Address");
						change = s.next();
						break; 
					case 12: 
						System.out.println("Returning to Main Menu"); 
						continue; 

					}
					if(!mc.equals(null)) {
						if (choice != 12) {
							mc = cdao.updateInfo(columnName, change, ssn, cardNum);
							System.out.printf("\n %10s %15s %10s %16s %8s %15s %8s %15s %8s %10s %20s\n", "First Name", "Middle Name", "Last Name", 
									"Street", "Apt #", "City", "State", "Country", "Zip", "Phone #", "Email Address \n");
							System.out.format("%10s %15s %10s %16s %8s %15s %8s %15s %8s %10s %20s \n",
									mc.getFirstName(), mc.getMiddleName(), mc.getLastName(), mc.getStreetName(), mc.getAptNo(), mc.getCustomerCity(),
									mc.getCustomerState(), mc.getCustomerCountry(), mc.getCustomerZip(), mc.getCustomerPhone(), mc.getCustomerEmail());
						}
					}

				}
			}
			catch(Exception e) {
				System.out.println("No records to display"); 
			}


		}
	

	
		public void menu() {
			System.out.println("\nSelect Field To Edit:");
			System.out.println("1. First Name");
			System.out.println("2. Middle Name");
			System.out.println("3. Last Name");
			System.out.println("4. Street Name");
			System.out.println("5. Apt #");
			System.out.println("6. City");
			System.out.println("7. State");
			System.out.println("8. Country");
			System.out.println("9. Zip Code");
			System.out.println("10. Phone Number");
			System.out.println("11. Email Address");
			System.out.println("12.Exit"); 
		}
	
		public void viewMonthlyBill() {
			try {
				System.out.println("Please Enter Card Number:");
				String cardNum = s.next(); 
				if(cardNum.length() != 16) {
					throw new Exception();
				}
				System.out.println("Please Enter Desired Month:");
				int month = s.nextInt();
				if(month < 1 || month > 12) {
					throw new Exception(); 
				}
				System.out.println("Please Enter Desired Year:");
				String year = s.next();


				CustomerDao cd = new CustomerDao(); 
				List <Customer> clist = cd.viewMonthlyBill(month,year,cardNum); 
				if(!clist.isEmpty()) {
					System.out.printf("%30s %23s \n", "Transaction Type", "Transaction Value"); 
					System.out.println("\t ----------------------------------------------------"); 
					for(Customer c : clist) { 
						System.out.format("%30s %23s \n", c.getType(), "$" + String.format("%.2f",c.getValue())); 
					};
					System.out.println(); 
					System.out.println();
					System.out.println("\t Number of Transactions = " + clist.size());
				}
			}

			catch(Exception e) {
				System.out.println("No records to display"); 
			}

		}
	
		public void displayBetweenDates() {
			try {
				System.out.println("Please Enter Card Number"); 
				String cardNum = s.next();
				if(cardNum.length() != 16) {
					throw new Exception(); 
				}
				System.out.println("Please Enter First Date: (ex: y/m/d 2018/10/1) ");
				String date1 = s.next(); 
				System.out.println("Please Enter Second Date: (ex: y/m/d 2018/10/1) ");
				String date2 = s.next();

				String formatdate1 = date1.replaceAll("/","");
				String formatdate2 = date2.replaceAll("/", ""); 
				CustomerDao cd = new CustomerDao(); 
				List<Customer> cl = cd.displayBetweenDates(cardNum, formatdate1, formatdate2); 
				if(!cl.isEmpty()) {
					System.out.printf("\t %20s %20s %15s \n", "Transaction Type", "Transaction Value", "Date"); 
					System.out.println("\t ---------------------------------------------------------------"); 
					for(Customer c: cl) {
						System.out.format("\t %20s %20s %15s \n", c.getType(), "$" + c.getValue(), c.getDate());
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

}


	
	




