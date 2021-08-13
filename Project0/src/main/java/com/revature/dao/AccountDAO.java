package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.model.Account;

public interface AccountDAO {

	public abstract List<Account> getAllAccounts() throws SQLException;
	
	public abstract Account getAccountById(int clientId, int accountId) throws SQLException;

	List<Account> getAllAccountsFromClient(int clientId) throws SQLException;
	
}
