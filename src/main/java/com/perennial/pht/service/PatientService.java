package com.perennial.pht.service;

import com.perennial.pht.DAL.PatientDAL;
import com.perennial.pht.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatientService {
@Autowired
    public PatientDAL patientDAL;

    public Patient testbyID(long id) {
        return patientDAL.testbyID(id);
    }

    public List<Patient> getAllPatients() {
        return patientDAL.getAllPatients();
    }

    public Patient createRecord(Patient patient) {
        return patientDAL.createRecord(patient);
    }

    public ResponseEntity<Patient> getPatientById(long patientId) {
        return patientDAL.getPatientById(patientId);
    }

    public ResponseEntity<Patient> updatePatient(long patientId, Patient patientDetails) {
        return patientDAL.updatePatient(patientId, patientDetails);
    }

    public ResponseEntity<HttpStatus> deletePatient(long patientId) {
        return patientDAL.deletePatient(patientId);
    }
}
