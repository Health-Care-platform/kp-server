package org.kp.restws.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Component
@Scope("prototype")
@ApiModel(description = "Patient infomation for validating")
public class Patient {
	
	@Id
	/*
	 * @GeneratedValue(strategy = GenerationType.TABLE,generator = "mednum")
	 * 
	 * @TableGenerator(name = "mednum",initialValue = 103)
	 */
	
	private Integer mediacalNumber;
	@ApiModelProperty(notes = "Should have more than 2 Characters")
	@Size(min = 2,message = "Must have more than 2 Char")
	private String firstName;
	
	@Size(min = 2,message = "Must have more than 2 Char")
	@ApiModelProperty(notes = "Should have more than 2 Characters")
	private String lastName;
	
	@FutureOrPresent(message = "Must be in pressent or future")
	@ApiModelProperty(notes = "Must be in the present or future")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
	private Date appointment;
	
	@Size(min = 2,message = "Must have more than 2 Char")
	@ApiModelProperty(notes = "Should have more than 2 Characters")
	private String doctor;
	
//	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<PatientMessage> messaege;
	private String email;
	private String message;
	
	private int port;
	
	public Patient() {
		// TODO Auto-generated constructor stub
	}
	
	public Patient(Integer medicalNuber, String firstName, String lastName, Date appointment, String doctor,String email,int port) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.appointment = appointment;
		this.doctor = doctor;
		this.mediacalNumber = medicalNuber;
		this.email = email;
		this.port=port;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getappointment() {
		return appointment;
	}
	public void setappointment(Date appointment) {
		this.appointment = appointment;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public Integer getMediacalNumber() {
		return mediacalNumber;
	}
	public void setMediacalNumber(Integer mediacalNumber) {
		this.mediacalNumber = mediacalNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String  message) {
		this. message =  message;
	}

	
	
//	public Date getAppointment() {
//		return appointment;
//	}
//
//	public void setAppointment(Date appointment) {
//		this.appointment = appointment;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "Patient [mediacalNumber=" + mediacalNumber + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", appointment=" + appointment + ", doctor=" + doctor + ", email=" + email + ", message=" + message
				+ ", port=" + port + "]";
	}

	

	
	
//	public List<PatientMessage> getMessaege() {
//		return messaege;
//	}
//
//	public void setMessaege(List<PatientMessage> messaege) {
//		this.messaege = messaege;
//	}

	
	
	

}
