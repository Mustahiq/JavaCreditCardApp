package resources;

public class myQuries {
	// Display Total Transactions By Type 
	public final static String totaByType = "select sum(transaction_value), count(*)" +
			"from CDW_SAPP_CREDITCARD " +
			"where TRANSACTION_TYPE = ? " +
			"GROUP by TRANSACTION_TYPE";

	// Display Transactions using customers zipcode, the month and the year 
	public final static String displayByZipMonthYear = "SELECT t.* " +
			"FROM CDW_SAPP_CREDITCARD AS t INNER JOIN CDW_SAPP_CUSTOMER AS c ON t.CUST_SSN=c.SSN " +
			"WHERE c.CUST_ZIP=? AND t.MONTH=? AND t.YEAR=? " +
			"ORDER BY t.DAY DESC";
	
	// Display number of transactions based on type
	public final static String transactionByType = "SELECT count(transaction_type), sum(transaction_value) " +
			"FROM CDW_SAPP_CREDITCARD " + 
			"WHERE transaction_type=? " +
			"Group By TRANSACTION_TYPE"; 
	
	// Display transactions based on State
	public final static String transactionByState = "Select count(transaction_type), sum(transaction_value) " + 
			"FROM CDW_SAPP_CREDITCARD " + 
			"JOIN CDW_SAPP_BRANCH using (branch_code) " + 
			"Where branch_state = ? " + 
			"Group By branch_state"; 
	
	// Display customer account details 
	public final static String displayAccount = "Select * " + 
			"FROM CDW_SAPP_CUSTOMER " + 
			"WHERE credit_card_no = ? AND ssn = ?"; 

	// Let the user update a category in the account
	public final static String updateAccountInfo = "Update cdw_sapp_customer " + 
			"Set %s = ? " + 
			"Where ssn = ? AND credit_card_no = ? "; 
	
	// Display the bill for customer for selected month
	public final static String viewMonthlyBill = "SELECT transaction_type, transaction_value " + 
			"FROM cdw_sapp_creditcard " + 
			"WHERE (month = ? AND year = ?) AND (credit_card_no = ?)"; 
	
	//Display transactions between 2 date ranges 
	public final static String displayBetweenDates = "select t.*, concat(t.year, t.month, t.day) AS ymd " +  
			"from CDW_SAPP_CREDITCARD t left join cdw_sapp_customer c on t.cust_ssn = c.ssn " +  
			"where t.credit_card_no = ? " + 
			"and (concat(t.year, t.month, t.day) between ? AND ? ) " + 
			"order by ymd;"; 
}


			

