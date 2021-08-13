package com.revature.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dto.ExceptionMessageDTO;
import com.revature.exception.BadParameterException;
import com.revature.exception.ClientNotFoundException;
import com.revature.exception.DatabaseException;
import com.revature.exception.AccountNotFoundException;

import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;

public class ExceptionController implements Controller {

	private Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	private ExceptionHandler<DatabaseException> databaseExceptionHandler = (e, ctx) -> {
		logger.error("DatabaseException occurred from " + ctx.method() + " " + ctx.path() +  ". Message is " + e.getMessage());
		
		ctx.status(500); // 500 means "Internal Server Error"
		
		// Here we encapsulate the exception message into a DTO that will be sent as JSON back to the user
		ExceptionMessageDTO messageDTO = new ExceptionMessageDTO();
		messageDTO.setMessage(e.getMessage());
		
		ctx.json(messageDTO);
	};
	
	private ExceptionHandler<ClientNotFoundException> clientNotFoundExceptionHandler = (e, ctx) -> {
		logger.info("ClientNotFoundException occurred from " + ctx.method() + " " + ctx.path() +  ". Message is " + e.getMessage());

		ctx.status(404); // 404 is "Not Found"
		
		ExceptionMessageDTO messageDTO = new ExceptionMessageDTO();
		messageDTO.setMessage(e.getMessage());
		
		ctx.json(messageDTO);
	};
	
	private ExceptionHandler<ClientNotFoundException> AccountNotFoundExceptionHandler = (e, ctx) -> {
		logger.info("AccountNotFoundExceptionHandler occurred from " + ctx.method() + " " + ctx.path() +  ". Message is " + e.getMessage());

		ctx.status(404); // 404 is "Not Found"
		
		ExceptionMessageDTO messageDTO = new ExceptionMessageDTO();
		messageDTO.setMessage(e.getMessage());
		
		ctx.json(messageDTO);
	};
	
	private ExceptionHandler<BadParameterException> badParameterExceptionHandler = (e, ctx) -> {
		logger.info("BadParameterException occurred from " + ctx.method() + " " + ctx.path() +  ". Message is " + e.getMessage());

		ctx.status(400);
		
		ExceptionMessageDTO messageDTO = new ExceptionMessageDTO();
		messageDTO.setMessage(e.getMessage());
		
		ctx.json(messageDTO);
	};
	
	@Override
	public void mapEndpoints(Javalin app) {
		app.exception(DatabaseException.class, databaseExceptionHandler);
		app.exception(ClientNotFoundException.class, clientNotFoundExceptionHandler);
		app.exception(ClientNotFoundException.class, clientNotFoundExceptionHandler);
		app.exception(BadParameterException.class, badParameterExceptionHandler);
	}

}
