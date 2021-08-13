package com.revature.model;

import java.util.Objects;

public class Account {

	private int id;
	private String accountNumber;
	private String accountType;
	private double amount;
	private String clientName;
	private int clientId;
	
	
	public Account() {
		super();
	}
	
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Account(int id, String acctnum, String accttype, double amount) {
		this.id = id;
		this.accountNumber = acctnum;
		this.accountType = accttype;
		this.amount = amount;
	}

	public Account(int id, String acctnum, String accttype, double amount, int cl_id) {
		this.id = id;
		this.accountNumber = acctnum;
		this.accountType = accttype;
		this.amount = amount;
		this.setClientId(cl_id);
	}


	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return accountNumber;
	}

	public void setName(String name) {
		this.accountNumber = name;
	}
	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, accountType, amount, clientName, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(accountNumber, other.accountNumber) && Objects.equals(accountType, other.accountType)
				&& Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(clientName, other.clientName) && id == other.id;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountNumber=" + accountNumber + ", accountType=" + accountType + ", amount="
				+ amount + ", clientName=" + clientName + "]";
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
}
