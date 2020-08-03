package org.kp.dao;

import org.kp.restws.model.Patient;
import org.kp.restws.model.PatientMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientMessageRepositry extends JpaRepository<PatientMessage, Integer> {

}
