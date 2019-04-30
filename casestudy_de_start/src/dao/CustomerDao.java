package dao;

import java.util.ArrayList;
import java.util.List;
import model.Customer;
import resources.myQuries;
// use db_abstract to connect to db
public class CustomerDao extends dbconnection_abstract {
	public Customer displayAccount(String cardnum, String ssn) throws Exception {
		myconnection(); 
		ps = con.prepareStatement(myQuries.displayAccount); 
		ps.setString(1, cardnum);
		ps.setString(2, ssn);
		rs = ps.executeQuery();  
		Customer c = new Customer(); 
		if (rs.next()) {
			c.setFirstName(rs.getString("First_Name"));
			c.setMiddleName(rs.getString("Middle_Name"));
			c.setLastName(rs.getString("Last_Name"));
			c.setSsn(rs.getInt("ssn"));
			c.setCardNo(rs.getString("credit_card_no"));
			c.setAptNo(rs.getInt("apt_No"));
			c.setStreetName(rs.getString("street_name"));
			c.setCustomerCity(rs.getString("cust_city")); 
			c.setCustomerState(rs.getString("cust_state"));
			c.setCustomerCountry(rs.getString("cust_country"));
			c.setCustomerZip(rs.getInt("cust_zip"));
			c.setCustomerPhone(rs.getInt("cust_phone"));
			c.setCustomerEmail(rs.getString("cust_email"));
			
			return c;
		}
		return null; 
	}
	
	public List<Customer> viewMonthlyBill(int month, String year, String cardNum) throws Exception {
		List <Customer> clist = new ArrayList<Customer>(); 
		myconnection(); 
		ps = con.prepareStatement(myQuries.viewMonthlyBill); 
		ps.setInt(1, month);
		ps.setString(2, year);
		ps.setString(3, cardNum);
		rs = ps.executeQuery();  
		
		while(rs.next()) {
			Customer c = new Customer(); 
			c.setValue(rs.getDouble("TRANSACTION_VALUE")); 
			c.setType(rs.getString("Transaction_type"));
			
			clist.add(c); 
		}
			return clist; 
			
	}
	
	public List<Customer> displayBetweenDates(String cardNum, String formatdate1, String formatdate2) throws Exception {
		List <Customer> clist = new ArrayList<Customer>(); 
		myconnection(); 
		ps = con.prepareStatement(myQuries.displayBetweenDates); 
		ps.setString(1, cardNum);
		ps.setString(2, formatdate1);
		ps.setString(3, formatdate2);
		rs = ps.executeQuery(); 
		
		while(rs.next()) {
			Customer c = new Customer(); 
			c.setValue(rs.getInt("TRANSACTION_VALUE"));
			c.setType(rs.getString("TRANSACTION_TYPE"));
			c.setDate(rs.getString("ymd"));
			
			clist.add(c); 
		}
		
		return clist;
		
	}
	
	
	public Customer updateInfo(String columnName , String change, String ssn, String cardNum){
		int success; 
		try {
			myconnection(); 
			ps = con.prepareStatement(String.format(myQuries.updateAccountInfo, columnName) );
			ps.setString(1, change);
			ps.setString(2, ssn);
			ps.setString(3, cardNum);
			success = ps.executeUpdate(); 
		} catch (Exception e) {
			success = 0; 
			e.printStackTrace();
		}
		if(success < 1) {
		 System.out.println("No changes made"); 
		}else {
			Customer c = new Customer();
			try {
				c = displayAccount(cardNum,ssn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return c; 
		}
		return null;
		
	}
	
	
	
}

