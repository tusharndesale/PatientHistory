package com.perennial.pht.service.serviceInterfaces;

import com.perennial.pht.model.Doctor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IdoctorService {

    public List<Doctor> getAllDoctors();

    public Doctor createRecord(Doctor doctor);

    public ResponseEntity<Doctor> getDoctorById(Integer id);

    public ResponseEntity<Doctor> updateDoctor(Integer id, Doctor doctorDetails);

    public ResponseEntity<HttpStatus> deleteDoctor(Integer id);

    List<String> addVitals(List<String> vitalList);
}
