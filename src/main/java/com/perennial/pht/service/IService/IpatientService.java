package com.perennial.pht.service.IService;

import com.perennial.pht.model.Patient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IpatientService {

    List<Patient> getAllPatients();

    Patient createRecord(Patient patient);

    ResponseEntity<Patient> getPatientById(long id);

    ResponseEntity<Patient> updatePatient(long id, Patient patientDetails);

    ResponseEntity<HttpStatus> deletePatient(long id);

    Patient testbyID(long id);
}
