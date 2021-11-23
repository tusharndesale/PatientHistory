package com.perennial.pht.dao.IDao;

import com.perennial.pht.model.Patient;
import com.perennial.pht.model.Vitals;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPatientDao {
    List<Patient> getAllPatients();

    Patient createRecord(Patient patient);

    ResponseEntity<Patient> getPatientById(long id);

    ResponseEntity<Patient> updatePatient(long id, Patient patientDetails);

    ResponseEntity<HttpStatus> deletePatient(long id);

    Patient testbyID(long id);

    Vitals addPatientVital(Vitals vitalValues);


    List<Vitals> getVitaldetails(Integer patientId);
}
