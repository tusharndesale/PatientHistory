package com.perennial.pht.service;

import com.perennial.pht.DAL.PatientDAL;
import com.perennial.pht.model.Patient;
import com.perennial.pht.model.Vitals;
import com.perennial.pht.service.IService.IpatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatientService implements IpatientService {
@Autowired
    public PatientDAL patientDAL;

    public Patient testbyID(long id) {
        return patientDAL.testbyID(id);
    }

    @Override
    public Vitals addPatientVital(Vitals vitalValues) {
        return patientDAL.addPatientVital(vitalValues);
    }

    @Override
    public List<Vitals> getVitaldetails(Integer patientId) {
        return patientDAL.getVitaldetails(patientId);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientDAL.getAllPatients();
    }

    @Override
    public Patient createRecord(Patient patient) {
        return patientDAL.createRecord(patient);
    }

    @Override
    public ResponseEntity<Patient> getPatientById(long patientId) {
        return patientDAL.getPatientById(patientId);
    }

    @Override
    public ResponseEntity<Patient> updatePatient(long patientId, Patient patientDetails) {
        return patientDAL.updatePatient(patientId, patientDetails);
    }

    @Override
    public ResponseEntity<HttpStatus> deletePatient(long patientId) {
        return patientDAL.deletePatient(patientId);
    }

}
