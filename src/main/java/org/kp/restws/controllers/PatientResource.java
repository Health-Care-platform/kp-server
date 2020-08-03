package org.kp.restws.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.kp.dao.ToDataBase;
import org.kp.restws.exception.PatientNotException;
import org.kp.restws.model.Patient;


@RestController
public class PatientResource {
	
	@Autowired
	private ToDataBase dao;
	
	
	@GetMapping("/patients")
	public List<Patient> getPatients() throws Exception{
		
		 List<Patient> getpatients = dao.getpatients();
		 if (getpatients == null) {
			throw new Exception("There is nothing in the List");
		}else {
			return getpatients;
		}
	}
	
	@GetMapping("/patients/{medicalId}")
	public EntityModel<Patient> getInfo(@PathVariable int medicalId) throws Exception {

		
		Patient findOne = dao.findOne(medicalId);
		if (findOne==null) {
			throw new PatientNotException("Id :" + medicalId);
		}else {
			// must have hateous dependency
			//also provide all users: "all-users", SERVER_PATH + "/patiennrs"
			//call for all users: getPatients()
					
			EntityModel<Patient> resourse = EntityModel.of(findOne);
			
			WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getPatients());
			//HATEOAS
			
			resourse.add(linkTo.withRel("all-users"));
			return resourse;
		}
	
	}
	
	@PostMapping("/patients")
	public ResponseEntity<Patient> addPatient(@Valid @RequestBody Patient patient) {
		Patient pat =  dao.save(patient);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{medicalId}").buildAndExpand(pat.getMediacalNumber()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/patients/{medicalId}")
	public ResponseEntity<Patient> deleteAppintment(@PathVariable int medicalId) {
		Patient deletePatient = dao.deletePatient(medicalId);
		if (deletePatient== null) {
			throw new PatientNotException("Id :" + medicalId);
		}else {
			
			return ResponseEntity.noContent().build();
		}
		
	}
	
	
}
