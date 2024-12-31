package tn.essat.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class EmailAlreadyExistsException extends RuntimeException{
	

		public EmailAlreadyExistsException(String message)

		{
			super (message);
			
		}

}
