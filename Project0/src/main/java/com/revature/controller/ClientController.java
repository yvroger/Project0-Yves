package com.revature.controller;

import java.util.List;

import com.revature.dto.AddOrEditClientDTO;
import com.revature.model.Client;
import com.revature.service.ClientService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ClientController implements Controller {

	private ClientService clientService;
	
	public ClientController() {
		this.clientService = new ClientService();
	}
	
	private Handler getAllClients = (ctx) -> {	
		
//		if (ctx.queryParam("ageLessThan")!= null && ctx.queryParam("ageMoreThan") != null) {
//			clientService.getClientsWithAgeBetween(ctx.queryParam("ageLessThan"), ctx.queryParam("ageMoreThan"));
//		} else if (ctx.queryParam("ageLessThan") != null) {
//			clientService.getClientsWithAgeBetween(ctx.queryParam("ageLessThan"), "-1");
//		} else if (ctx.queryParam("ageMoreThan") != null) {
//			clientService.getClientsWithAgeBetween("-1", ctx.queryParam("ageMoreThan"));
//		}
		
		List<Client> clients = clientService.getAllClients();
		
		ctx.status(200); // 200 means OK
		ctx.json(clients);
	};
	
	private Handler getClientById = (ctx) -> {
		String clientid = ctx.pathParam("clientid");
		
		Client client = clientService.getClientById(clientid);
		ctx.json(client);
	};
	
	private Handler addClient = (ctx) -> {
		AddOrEditClientDTO clientToAdd = ctx.bodyAsClass(AddOrEditClientDTO.class);
		
		Client addedClient = clientService.addClient(clientToAdd);
		ctx.json(addedClient);
	};
	
	private Handler editClient = (ctx) -> {
		AddOrEditClientDTO clientToEdit = ctx.bodyAsClass(AddOrEditClientDTO.class);
		
		String clientId = ctx.pathParam("clientid");
		Client editedClient = clientService.editClient(clientId, clientToEdit);
		
		ctx.json(editedClient);
	};
	
	private Handler deleteClient = (ctx) -> {
		String clientId = ctx.pathParam("clientid");
		
		clientService.deleteClient(clientId);
	};
	
	@Override
	public void mapEndpoints(Javalin app) {
		app.get("/client", getAllClients);
		app.get("/client/:clientid", getClientById);
		app.post("/client", addClient);
		app.put("/client/:clientid", editClient);
		app.delete("/client/:clientid", deleteClient);
	}

}
