package org.kp.restws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Id Not Found")
public class PatientNotException extends RuntimeException {

	public PatientNotException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	

	
	

}
