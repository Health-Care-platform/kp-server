package org.kp.dao;

import org.kp.restws.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepositry extends JpaRepository<Patient, Integer> {

}
