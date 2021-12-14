package com.perennial.pht.dao;

import com.perennial.pht.dao.daoInterfaces.IPatientDao;
import com.perennial.pht.excel.ExcelToList;
import com.perennial.pht.exception.ResourceNotFoundException;
import com.perennial.pht.model.Patient;
import com.perennial.pht.model.Vitals;
import com.perennial.pht.repository.PatientRepository;
import com.perennial.pht.repository.VitalRepository;
import com.perennial.pht.utilities.CommonUtility;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class PatientDao implements IPatientDao {
    public static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired(required = false)
    SessionFactory sessionfactory;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private VitalRepository vitalRepository;
    private CommonUtility utility;
    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient createRecord(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public ResponseEntity<Patient> getPatientById(Integer id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient Does not Exist With id " + id));
        return ResponseEntity.ok(patient);
    }

    @Override
    public ResponseEntity<Patient> updatePatient(Integer id, Patient patientDetails) {
        Patient updatePatient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient Does not Exist With id " + id));
        updatePatient.setName(patientDetails.getName());
        updatePatient.setGender(patientDetails.getGender());
        updatePatient.setAddress(patientDetails.getAddress());
        updatePatient.setMobileNo(patientDetails.getMobileNo());
        patientRepository.save(updatePatient);
        return ResponseEntity.ok(updatePatient);
    }

    @Override
    public ResponseEntity<HttpStatus> deletePatient(Integer id) {
        Patient findPatient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient Does not Exist With id " + id));
        patientRepository.delete(findPatient);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public Patient testbyID(Integer id) {

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
    public List<Patient> uploadFile(MultipartFile file) {
        try {
            List<List<Patient>> resultList = ExcelToList.excelToPatient(file.getInputStream(), sessionfactory);
            patientRepository.saveAll(resultList.get(0));
            return resultList.get(1);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
