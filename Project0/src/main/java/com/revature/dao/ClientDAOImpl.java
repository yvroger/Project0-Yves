package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dto.AddOrEditClientDTO;
import com.revature.model.Client;
import com.revature.util.ConnectionUtility;

public class ClientDAOImpl implements ClientDAO {

	@Override
	public List<Client> getAllClients() throws SQLException {
		// Construct a List for Clients
		// This will store all of the Clients that exist in the database and pass it back to the user
		List<Client> clients = new ArrayList<>();
		
		// This is known as try-with-resources
		// Try with resources works with any type of class that implements the AutoCloseable interface
		// What try with resources will do, is once all of the code inside of the try block is executed (or an exception is caught if we
		// have a catch block, or the finally block, etc.), it will automatically call .close() on that object
		// So, in this case, it will automatically close the connection for us.
		try (Connection con = ConnectionUtility.getConnection()) {
			// 1. get a Connection object
			
			// 2. Obtain some sort of Statement object (Statement, PreparedStatement, CallableStatement)
			// In our case, we'll demonstrate the usage of Statement here
			Statement stmt = con.createStatement();
			
			// 3. Execute the query
			String sql = "SELECT * FROM client";
			ResultSet rs = stmt.executeQuery(sql);
			
			// 4. Process the results
			// ResultSet does not actually contain the data. It is a pointer to the results that exist over on the database, after the
			// database has executed our query
			// When we iterate over the results using our ResultSet object, that is the moment when it retrieves each result one by one
			while (rs.next()) {
				// rs.next() moves the "cursor" forward by one. It will return true if it successfully moved forward, and false if 
				// there are no more rows available. Therefore our while loop will be broken out of once we have already iterated
				// over the last row
				
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String clientType = rs.getString("clientType");
				int age = rs.getInt("age");
				
				Client client = new Client(id, name, clientType, age);
//				Client client = new Client(id, name, age);
				
				clients.add(client);
			}
		}
		
		return clients;
	}

	@Override
	public Client getClientById(int id) throws SQLException {
		
		// try with resources can stand by itself
		// But if we're not using try with resources,
		// We must have try-catch-...-finally
		// try-catch
		// try-catch-catch-...
		// try-finally
		try (Connection con = ConnectionUtility.getConnection()) {
			String sql = "SELECT * FROM client WHERE id = ?";
			// The '?' is a placeholder for a value that we want to provide for our query
			// The purpose of a PreparedStatement object is to represent a template
			// That we can set values for
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			// So, we can pass in the id parameter
			pstmt.setInt(1, id);
			
			// Execute the query
			ResultSet rs = pstmt.executeQuery();
			
			// We know that if we query by id, id's are unique, and therefore we should expect
			// either no result, or just 1 result
			// Therefore unlike the getAllClients() example, we do not need a while loop
			// We can just call rs.next() once
			if (rs.next()) {
				int client_id = rs.getInt("id");
				String name = rs.getString("name");
				String clientType = rs.getString("clientType");
				int age = rs.getInt("age");
				
				Client client = new Client(client_id, name, clientType, age);
				
				return client;
			} else {
				return null;
			}
		}
	}

	/************************************************************************************************************
	 * ********** Add a new client	********************************
	 */
	
	@Override
	public Client addClient(AddOrEditClientDTO client) throws SQLException {
		try (Connection con = ConnectionUtility.getConnection()) {
			String sql = "INSERT INTO client (name, clientType, age) VALUES (?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, client.getName());
			pstmt.setString(2, client.getClientType());
			pstmt.setInt(3, client.getAge());
			
			int recordsUpdated = pstmt.executeUpdate(); // Use executeUpdate when working with INSERT, UPDATE, or DELETE
			// Use executeQuery() if you need a ResultSet (obviously coming from using SELECT)
			if (recordsUpdated != 1) {
				throw new SQLException("Could not insert a client record");
			}
			
			// Notice how the return type I have for addClient is Client
			// I want to be able to return an object with the properties that we are inserting for the record
			// But also include the auto-generated id
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				Client createdClient = new Client(generatedKeys.getInt(1), client.getName(), client.getClientType(), client.getAge());
				
				return createdClient;
			} else {
				throw new SQLException("Autoincrement id could not be generated for Client");
			}
			
		}
	}

	@Override
	public Client editClient(int clientId, AddOrEditClientDTO client) throws SQLException {
		try (Connection con = ConnectionUtility.getConnection()) {
			String sql = "UPDATE client SET name = ?, age = ? WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, client.getName());
			pstmt.setInt(2, client.getAge());
			pstmt.setInt(3, clientId);
			
			int recordsUpdated = pstmt.executeUpdate();
			if (recordsUpdated != 1) {
				throw new SQLException("Record was not able to be updated");
			}
			
			return new Client(clientId, client.getName(), client.getClientType(), client.getAge());
		}
	}

	@Override
	public void deleteClient(int clientId) throws SQLException {
	
		try (Connection con = ConnectionUtility.getConnection()) {
			String sql = "DELETE FROM client WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, clientId);
			
			int recordsDeleted = pstmt.executeUpdate();
			
			// if it is not 1, we know that no records were actually deleted
			if (recordsDeleted != 1) {
				throw new SQLException("Record was not able to be deleted");
			}
		}
	}

}
