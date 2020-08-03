package org.kp.restws.controllers;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.kp.dao.PatientMessageRepositry;
import org.kp.dao.PatientRepositry;
import org.kp.restws.exception.PatientNotException;
import org.kp.restws.model.Patient;
import org.kp.restws.model.PatientMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path = "/jpa")
public class PatientJPAResource {
	@Autowired
	private Environment env;

	@Autowired
	private PatientRepositry repo;

	@Autowired
	private PatientMessageRepositry pmRepo;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@GetMapping("/patients")
	public List<Patient> getPatients() throws Exception {

		List<Patient> getpatients = repo.findAll();
		if (getpatients == null) {
			throw new Exception("There is nothing in the List");
		} else {
			return getpatients;
		}
	}

	@GetMapping("/patients/{medicalId}")
	public Patient getInfo(@PathVariable int medicalId) throws Exception {

		Optional<Patient> findOne = repo.findById(medicalId);
		if (!findOne.isPresent()) {
			throw new PatientNotException("Id :" + medicalId);
		} else {
			// must have hateous dependency
			// also provide all users: "all-users", SERVER_PATH + "/patiennrs"
			// call for all users: getPatients()
			Patient pat = findOne.get();

			EntityModel<Patient> resourse = EntityModel.of(findOne.get());

			WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getPatients());
			// HATEOAS

			resourse.add(linkTo.withRel("all-users"));
			logger.info("{}", pat);
			return findOne.get();
		}

	}

	@PostMapping("/patients")
	public Patient addPatient(@Valid @RequestBody Patient patient) {

		Patient pat = repo.save(patient);
	
//		Optional<Patient> findById = repo.findById(pat.getMediacalNumber());
//		if(!findById.isPresent()) {
//			throw new PatientNotException("Id :" + pat.getMediacalNumber());
//		}
		
		System.out.println(pat.getMessage());
		
//		List<PatientMessage> messaege = pat.getMessaege();
//		
//		
//		for (PatientMessage patientMessage : messaege) {
//			patientMessage.setPatient(pat);
//			patientMessage.setTimestamp(new Date());
//			pmRepo.save(patientMessage);
//		}
		
				
		System.err.println(pat);			
		
		
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{medicalId}")
//				.buildAndExpand(pat.getMediacalNumber()).toUri();
//
//		return ResponseEntity.created(location).build();
		
		pat.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		logger.info("{}", pat);
		return pat;
	}

	@DeleteMapping("/patients/{medicalId}")
	public ResponseEntity<Patient> deleteAppintment(@PathVariable int medicalId) {
		Optional<Patient> deletePatient = repo.findById(medicalId);
		repo.delete(deletePatient.get());
		if (!deletePatient.isPresent()) {
			throw new PatientNotException("Id :" + medicalId);
		} else {

			return ResponseEntity.noContent().build();
		}

	}

//	@GetMapping("/patients/{medicalId}/posts")
//	public List<PatientMessage> getPatients(@PathVariable int medicalId) {
//
//		Optional<Patient> getpatients = repo.findById(medicalId);
//		if (!getpatients.isPresent()) {
//			throw new RuntimeException("There is nothing in the List");
//		} else {
//			return getpatients.get().getMessaege();
//		}
//	}
	
	@GetMapping("/patients/{medicalId}/posts")
	public String getPatients(@PathVariable int medicalId) {

		Optional<Patient> patients = repo.findById(medicalId);
		if (!patients.isPresent()) {
			throw new RuntimeException("There is nothing in the List");
		} else {
			return patients.get().getMessage();
		}
	}

	@PostMapping("/patients/{medicalId}/posts")
	public ResponseEntity<Patient> createPatientMessage(@PathVariable int medicalId, @RequestBody PatientMessage pm) {
		Optional<Patient> findById = repo.findById(medicalId);

		if (!findById.isPresent()) {
			throw new PatientNotException("Id :" + medicalId);
		}
			Patient pat = findById.get();
			
			pm.setPatient(pat);
			
			pmRepo.save(pm);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{medicalId}")
					.buildAndExpand(pm.getId()).toUri();

			return ResponseEntity.created(location).build();
		
	}
}
