package com.perennial.pht.DAL;

import com.perennial.pht.exception.ResourceNotFoundException;
import com.perennial.pht.model.Patient;
import com.perennial.pht.repository.PatientRepository;
import com.perennial.pht.service.IService.IpatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientDAL implements IpatientService {
 //   @Autowired
 //   SessionFactory sessionFactory;
@Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient createRecord(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public ResponseEntity<Patient> getPatientById(long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient Does not Exist With id "+id));
        return ResponseEntity.ok(patient);
    }

    @Override
    public ResponseEntity<Patient> updatePatient(long id, Patient patientDetails) {
        Patient updatePatient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient Does not Exist With id "+id));
        updatePatient.setName(patientDetails.getName());
        updatePatient.setGender(patientDetails.getGender());
        updatePatient.setAddress(patientDetails.getAddress());
        updatePatient.setMobileNo(patientDetails.getMobileNo());
        patientRepository.save(updatePatient);
        return ResponseEntity.ok(updatePatient);
    }

    @Override
    public ResponseEntity<HttpStatus> deletePatient(long id) {
        Patient findPatient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient Does not Exist With id "+id));
        patientRepository.delete(findPatient);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public Patient testbyID(long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient Does not Exist With id "+id));
        return patient;

    }
}
