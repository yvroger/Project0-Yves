package com.revature.service;

import java.sql.SQLException;
import java.util.List;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.ClientDAO;
import com.revature.dao.ClientDAOImpl;
import com.revature.exception.BadParameterException;
import com.revature.exception.DatabaseException;
import com.revature.exception.ClientNotFoundException;
import com.revature.model.Account;

public class AccountService {

	private AccountDAO accountDao;
	private ClientDAO clientDao;
	
	public AccountService() {
		this.accountDao = new AccountDAOImpl();
		this.clientDao = new ClientDAOImpl();
	}
	
	// Define a constructor that takes in AccountDAO and ClientDAO to allow us to "inject" mockito mocked objects into
	// a AccountService instance
	public AccountService(ClientDAO clientDao, AccountDAO accountDao) {
		this.clientDao = clientDao;
		this.accountDao = accountDao;
	}
	
	public List<Account> getAllAccounts() throws DatabaseException {
		List<Account> lstAccounts;
		try {
			lstAccounts = accountDao.getAllAccounts();

//			for (Client client : clients) {
//				List<Account> accounts = accountDao.getAllAccountsFromClient(client.getId());
//				client.setAccounts(accounts);
//			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Could not complete DAO operation when trying to display all accounts");
		}

		return lstAccounts;
	}
	
	public List<Account> getAllAccountsFromClient(String clientIdString) throws BadParameterException, DatabaseException, ClientNotFoundException {
		try {
			
			int clientId = Integer.parseInt(clientIdString);
			
			if(clientDao.getClientById(clientId) == null) {
				throw new ClientNotFoundException("Client with id " + clientId + " was not found");
			}
			
			List<Account> accounts = accountDao.getAllAccountsFromClient(clientId);
			
			return accounts;
			
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} catch (NumberFormatException e) {
			throw new BadParameterException(clientIdString + " was passed in by the user as the id, " + "but it is not an int");
		}
	}


		public Account getAccountById(String strClientId, String strAccountId) throws ClientNotFoundException, DatabaseException, BadParameterException {
			try {
				
				int clientId = Integer.parseInt(strClientId);
				
				
				if(clientDao.getClientById(clientId) == null) {
					throw new ClientNotFoundException("Client with id " + clientId + " was not found");
				}
				
				try {
					
					int accountId = Integer.parseInt(strAccountId);
					
					if(accountDao.getAccountById(clientId,accountId) == null) {
						throw new ClientNotFoundException("Account with id " + accountId + " was not found");
					}
					
					Account targetAccount = accountDao.getAccountById(clientId,accountId);
					return targetAccount;
					
					
					
				} catch (SQLException e) {
					throw new DatabaseException(e.getMessage());
				} catch (NumberFormatException e) {
					throw new BadParameterException(strClientId + " was passed in by the user as the id, " + "but it is not an int");
				}
				
				
				
			} catch (SQLException e) {
				throw new DatabaseException(e.getMessage());
			} catch (NumberFormatException e) {
				throw new BadParameterException(strClientId + " was passed in by the user as the id, " + "but it is not an int");
			}
		}
	
}
