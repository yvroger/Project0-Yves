package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Account;
import com.revature.util.ConnectionUtility;

public class AccountDAOImpl implements AccountDAO {


	@Override
	public List<Account> getAllAccounts() throws SQLException {
		
		// Construct a List for Accounts
		List<Account> accounts = new ArrayList<>();

		// 1. get a Connection object
		try (Connection con = ConnectionUtility.getConnection()) {
			
			// 2. Obtain some sort of Statement object (Statement, PreparedStatement, CallableStatement)
			Statement stmt = con.createStatement();
			
			// 3. Execute the query
			String sql = "SELECT * FROM account";
			ResultSet rs = stmt.executeQuery(sql);
			
			// 4. Process the results
			while (rs.next()) {
				
				int acctId = rs.getInt("accountid");
				String acctNum = rs.getString("accountNumber");
				String acctType = rs.getString("accountType");
				Double Amount = rs.getDouble("amount");
				
				Account account = new Account(acctId, acctNum, acctType, Amount);
				
				accounts.add(account);
			}
		}
		
		return accounts;
	}
	
	
	@Override
	public Account getAccountById(int clientId, int accountId) throws SQLException {
		try(Connection con = ConnectionUtility.getConnection()) {
			
			String sql = "SELECT * FROM account WHERE client_id = ? AND accountid = ?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, clientId);
			pstmt.setInt(2, accountId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				int id = rs.getInt("accountid");
				String acctNum = rs.getString("accountNumber");
				String amount = rs.getString("amount");
				Double client_Id = rs.getDouble("client_id");
				
				Account account = new Account(id, acctNum, amount, client_Id);
				
				return account;
			} else {
				return null;
			}
			
		}
	}


	@Override
	public List<Account> getAllAccountsFromClient(int clientId) throws SQLException {
		try(Connection con = ConnectionUtility.getConnection()) {
			List<Account> accounts = new ArrayList<>();
			
			String sql = "SELECT accountid, accountNumber, accountType, amount FROM account WHERE client_id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, clientId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("accountId");
				String acctNum = rs.getString("accountNumber");
				String acctType = rs.getString("accountType");
				double amount = rs.getDouble("amount");
				
				Account p = new Account(id, acctNum, acctType, amount);
				accounts.add(p);
			}
			
			return accounts;
		}
	}
}
