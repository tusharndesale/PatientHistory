package com.perennial.pht.service.serviceInterfaces;

import com.perennial.pht.model.Patient;
import com.perennial.pht.model.Vitals;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface IpatientService {

    List<Patient> getAllPatients();

    Patient createRecord(Patient patient);

    ResponseEntity<Patient> getPatientById(Integer id);

    ResponseEntity<Patient> updatePatient(Integer id, Patient patientDetails);

    ResponseEntity<HttpStatus> deletePatient(Integer id);

    Patient testbyID(Integer id);

    Vitals addPatientVital(Vitals vitalValues);

    List<Vitals> getVitaldetails(Integer patientId);

    List<Patient> uploadFile(MultipartFile file);

    ByteArrayInputStream load(List<String> headerList, List<Patient> issueRecordList);
}
