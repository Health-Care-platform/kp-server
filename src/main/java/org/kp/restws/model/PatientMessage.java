package org.kp.restws.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PatientMessage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator = "mednum")
	@TableGenerator(name = "mednum",initialValue = 10003)
	private Integer id;
	private String message;
	private Date timestamp;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Patient patient;
	
	public PatientMessage() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "PatientMessage [id=" + id + ", message=" + message + "]";
	}
	
	
	
	

}
