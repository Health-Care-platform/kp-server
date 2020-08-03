package org.kp.restws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "patient exists")
public class PatientExistException extends RuntimeException {
	public PatientExistException(String ex) {
		super(ex);
	}
}
