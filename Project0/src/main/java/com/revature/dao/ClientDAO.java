package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.dto.AddOrEditClientDTO;
import com.revature.model.Client;

public interface ClientDAO {

	public abstract List<Client> getAllClients() throws SQLException;
	
	/**
	 * This method returns a Client from the database
	 * 
	 * @param id is an int that represents the id
	 * @return Client a representation of client, or null if none was found
	 */
	public abstract Client getClientById(int id) throws SQLException;
	
	// Here we are making use of a parameter known as AddClientDTO, which is a Data Transfer Object
	// DTOs are classes that are used to pass data around that might not completely conform to the actual "Model" class
	// The "Model" class in this case is the Client class, which will define ALL of the attributes associated with the data inside the
	// database
	public abstract Client addClient(AddOrEditClientDTO client) throws SQLException;
	
	public abstract Client editClient(int clientId, AddOrEditClientDTO client) throws SQLException;
	
	public abstract void deleteClient(int clientId) throws SQLException;
}
