package org.kp.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.kp.restws.model.Patient;

@Repository
public class ToDataBase {

	private static int count = 103;
	private static List<Patient> patients = new ArrayList<Patient>();
	static {
		patients.add(new Patient(101,"Yash", "Patel",new Date(2020, 07, 27) , "Dr. Sai","Yash@gmail.com",0));
		patients.add(new Patient(102,"Druv", "Patel",new Date(2020, 07, 23) , "Dr. Sai","Druv@gmail.com",0));
		patients.add(new Patient(103,"Hari", "Patel",new Date(2020, 07, 24) , "Dr. Sai","Hari@gmail.com",0));
		
	}
	
	public List<Patient> getpatients(){
		return patients;
	}
	
	public Patient save(Patient patient) {
		if (patient.getMediacalNumber()==null) {
			patient.setMediacalNumber(++count);
		}
		patients.add(patient);
		return patient;
		
	}
	
	public Patient findOne(int medicalId) {
		for (Patient appointment : patients) {
			if (appointment.getMediacalNumber()==medicalId) {
				System.out.println(appointment.getFirstName());
				return appointment;
			}
		}
		return null;
	}
	public Patient deletePatient(int id) {
		ListIterator<Patient> listIterator = patients.listIterator();
		
		while (listIterator.hasNext()) {
			Patient next = listIterator.next();
			if (next.getMediacalNumber() == id) {
				listIterator.remove();
				return next;
			}
		}
		return null;
	}
}
