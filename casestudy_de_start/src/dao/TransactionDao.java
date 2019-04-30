package dao;

import java.util.ArrayList;
import java.util.List;

import model.Transaction;
import resources.myQuries;

public class TransactionDao extends dbconnection_abstract {
	public Transaction gettotalbyType(String type) throws Exception {
		myconnection();
		ps = con.prepareStatement(myQuries.totaByType);
		ps.setString(1, type);
		rs = ps.executeQuery();
		Transaction t = new Transaction();
		if(rs.next()) {
			t.setValue(rs.getInt("SUM(TRANSACTION_VALUE)"));
			t.setCount(rs.getInt("COUNT(*)"));
			return t;
		}		
		return null;
	}


	public List<Transaction> displayByZipMonthYear(String zip, int month, int year) throws Exception {
		List <Transaction> zlist = new ArrayList<Transaction>();		 
		myconnection();
		ps = con.prepareStatement(myQuries.displayByZipMonthYear);
		ps.setString(1, zip);
		ps.setInt(2, month);
		ps.setInt(3, year);
		rs = ps.executeQuery();

		while(rs.next()) {		
			Transaction t = new Transaction();
			t.setDay(rs.getInt("DAY"));
			t.setMonth(rs.getInt("MONTH"));
			t.setYear(rs.getInt("YEAR"));
			t.setCardNo(rs.getString("CREDIT_CARD_NO"));
			t.setSsn(rs.getInt("CUST_SSN"));
			t.setBranchCode(rs.getInt("BRANCH_CODE"));
			t.setType(rs.getString("TRANSACTION_TYPE"));
			t.setValue(rs.getInt("TRANSACTION_VALUE"));

			zlist.add(t); 

		}

		return zlist;
	}

	public Transaction transactionByType(String type) throws Exception {
		myconnection();
		ps = con.prepareStatement(myQuries.transactionByType);
		ps.setString(1, type);
		rs = ps.executeQuery();
		Transaction t = new Transaction();
		if(rs.next()) {
			t.setValue(rs.getInt("SUM(TRANSACTION_VALUE)"));
			t.setCount(rs.getInt("COUNT(TRANSACTION_TYPE)"));
			return t;
		}		
		return null;
	}

	public Transaction transactionByState(String state) throws Exception {
		myconnection(); 
		ps = con.prepareStatement(myQuries.transactionByState); 
		ps.setString(1, state);
		rs = ps.executeQuery(); 
		Transaction t = new Transaction(); 
		if(rs.next()) {
			t.setValue(rs.getInt("SUM(TRANSACTION_VALUE)"));
			t.setCount(rs.getInt("Count(TRANSACTION_TYPE)"));
			return t;
		}
		return null; 

	}



}