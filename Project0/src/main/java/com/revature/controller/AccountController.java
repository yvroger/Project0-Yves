package com.revature.controller;

import java.util.List;

import com.revature.model.Account;
import com.revature.service.AccountService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class AccountController implements Controller {

	private AccountService accountService;
	
	public AccountController() {
		this.accountService = new AccountService();
	}
	
	private Handler getAllAccounts = (ctx) -> {	
		
		List<Account> accounts = accountService.getAllAccounts();
		
		ctx.status(200); // 200 means OK
		ctx.json(accounts);
	};
	
	
	private Handler getAccountById = (ctx) -> {
		String clientId = ctx.pathParam("clientid");

		String accountId = ctx.pathParam("accountid");
		
		Account accountById = accountService.getAccountById(clientId, accountId);

		ctx.status(200); // 200 means OK
		ctx.json(accountById);
	};
	
	
	private Handler getAccountFromClient = (ctx) -> {
		String clientId = ctx.pathParam("clientid");
		
		List<Account> accountsFromClient = accountService.getAllAccountsFromClient(clientId);
		
		ctx.json(accountsFromClient);
	};
	
	@Override
	public void mapEndpoints(Javalin app) {
		app.get("/account", getAllAccounts);
		app.get("/client/:clientid/account/:accountid", getAccountById);
		app.get("/client/:clientid/account", getAccountFromClient);
	}

}
