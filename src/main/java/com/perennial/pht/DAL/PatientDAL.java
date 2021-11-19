package com.perennial.pht.DAL;

import com.perennial.pht.exception.ResourceNotFoundException;
import com.perennial.pht.model.Patient;
import com.perennial.pht.model.Vitals;
import com.perennial.pht.repository.PatientRepository;
import com.perennial.pht.repository.VitalRepository;
import com.perennial.pht.service.IService.IpatientService;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class PatientDAL implements IpatientService {
    @Autowired(required = false)
    SessionFactory sessionfactory;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private VitalRepository vitalRepository;

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
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient Does not Exist With id " + id));
        return ResponseEntity.ok(patient);
    }

    @Override
    public ResponseEntity<Patient> updatePatient(long id, Patient patientDetails) {
        Patient updatePatient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient Does not Exist With id " + id));
        updatePatient.setName(patientDetails.getName());
        updatePatient.setGender(patientDetails.getGender());
        updatePatient.setAddress(patientDetails.getAddress());
        updatePatient.setMobileNo(patientDetails.getMobileNo());
        patientRepository.save(updatePatient);
        return ResponseEntity.ok(updatePatient);
    }

    @Override
    public ResponseEntity<HttpStatus> deletePatient(long id) {
        Patient findPatient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient Does not Exist With id " + id));
        patientRepository.delete(findPatient);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public Patient testbyID(long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient Does not Exist With id " + id));
        return patient;

    }

    @Override
    public Vitals addPatientVital(Vitals vitalValues) {
        return vitalRepository.save(vitalValues);
    }

    @Override
    public List<Vitals> getVitaldetails(Integer patientId) {
     //   Vitals example = Vitals.builder().patientId(patientId).build();
      //  return vitalRepository.findAll(Example.of(example));

        Transaction tx = null;
        String hql=null;
        List<Vitals> result = new ArrayList<>();
        try{

            Session session = sessionfactory.openSession();
            tx = session.beginTransaction();
            hql = "FROM Vitals WHERE patientId = "+patientId;
            Query query = session.createQuery(hql);
            result = query.list();

            result.forEach(ele ->{
                System.out.println(ele.toString());
            });
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return result;
    }
}
