package com.perennial.pht.service;

import com.perennial.pht.dao.PatientDao;
import com.perennial.pht.model.Patient;
import com.perennial.pht.model.Vitals;
import com.perennial.pht.service.serviceInterfaces.IpatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class PatientService implements IpatientService {
    @Autowired
    public PatientDao patientDao;

    public Patient testbyID(long id) {
        return patientDao.testbyID(id);
    }

    @Override
    public Vitals addPatientVital(Vitals vitalValues) {
        return patientDao.addPatientVital(vitalValues);
    }

    @Override
    public List<Vitals> getVitaldetails(Integer patientId) {
        return patientDao.getVitaldetails(patientId);
    }

    @Override
    public void uploadFile(MultipartFile file) {
        patientDao.uploadFile(file);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientDao.getAllPatients();
    }

    @Override
    public Patient createRecord(Patient patient) {
        return patientDao.createRecord(patient);
    }

    @Override
    public ResponseEntity<Patient> getPatientById(long patientId) {
        return patientDao.getPatientById(patientId);
    }

    @Override
    public ResponseEntity<Patient> updatePatient(long patientId, Patient patientDetails) {
        return patientDao.updatePatient(patientId, patientDetails);
    }

    @Override
    public ResponseEntity<HttpStatus> deletePatient(long patientId) {
        return patientDao.deletePatient(patientId);
    }

}
