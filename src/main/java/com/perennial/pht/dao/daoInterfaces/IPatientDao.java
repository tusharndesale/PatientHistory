package com.perennial.pht.dao.daoInterfaces;

import com.perennial.pht.model.Patient;
import com.perennial.pht.model.Vitals;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPatientDao {
    List<Patient> getAllPatients();

    Patient createRecord(Patient patient);

    ResponseEntity<Patient> getPatientById(Integer id);

    ResponseEntity<Patient> updatePatient(Integer id, Patient patientDetails);

    ResponseEntity<HttpStatus> deletePatient(Integer id);

    Patient testbyID(Integer id);

    Vitals addPatientVital(Vitals vitalValues);


    List<Vitals> getVitaldetails(Integer patientId);

    List<Patient> uploadFile(MultipartFile file);

}
