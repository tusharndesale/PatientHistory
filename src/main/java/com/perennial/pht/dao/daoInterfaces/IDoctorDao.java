package com.perennial.pht.dao.daoInterfaces;

import com.perennial.pht.model.Doctor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IDoctorDao {

    List<Doctor> getAllDoctors();

    Doctor createRecord(Doctor doctor);

    ResponseEntity<Doctor> getDoctorById(long doctorId);

    ResponseEntity<Doctor> updateDoctor(long doctorId, Doctor doctorDetails);

    ResponseEntity<HttpStatus> deleteDoctor(long doctorId);

    List<String> addVitals(List<String> vitalList);
}
