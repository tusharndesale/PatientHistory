package com.perennial.pht.dao;

import com.perennial.pht.dao.daoInterfaces.IPatientDao;
import com.perennial.pht.excel.PatientExcel;
import com.perennial.pht.exception.ResourceNotFoundException;
import com.perennial.pht.model.Patient;
import com.perennial.pht.model.Vitals;
import com.perennial.pht.repository.PatientRepository;
import com.perennial.pht.repository.VitalRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientDao implements IPatientDao {
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
        return patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient Does not Exist With id " + id));

    }

    @Override
    public Vitals addPatientVital(Vitals vitalValues) {
        return vitalRepository.save(vitalValues);
    }

    @Override
    public List<Vitals> getVitaldetails(Integer patientId) {

        Transaction tx = null;
        String hql=null;
        List<Vitals> result = new ArrayList<>();
        try{

            Session session = sessionfactory.openSession();
            tx = session.beginTransaction();
            hql = "FROM Vitals WHERE patientId = "+patientId;
            Query query = session.createQuery(hql);
            result = query.list();
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public void uploadFile(MultipartFile file) {
        try {
            List<Patient> PatientList = PatientExcel.excelToPatient(file.getInputStream());
            patientRepository.saveAll(PatientList);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
