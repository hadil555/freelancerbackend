package tn.essat.exception;

import tn.essat.entities.Message;

public class MessageNotFoundException extends Exception{
	
	

	public MessageNotFoundException()
	{
		
	}
	public MessageNotFoundException(String message)

	{
		super (message);
		
	}

}
