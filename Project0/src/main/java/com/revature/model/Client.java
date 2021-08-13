package com.revature.model;

import java.util.List;
import java.util.Objects;

public class Client {

	private int id;
	private String name;
	private String clientType;
	private int age;
	
	List<Account> accounts;
	
	// No-args constructor
	public Client() {
		super();
	}
	
	// Parameterized constructor
	public Client(int id, String name, String clientType, int age) {
		super();
		this.id = id;
		this.name = name;
		this.clientType = clientType;
		this.age = age;
	}

	// Getters/Setters
	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accounts, age, clientType, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(accounts, other.accounts) && age == other.age
				&& Objects.equals(clientType, other.clientType) && id == other.id && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", clientType=" + clientType + ", age=" + age + ", accounts="
				+ accounts + "]";
	}

}
