package com.perennial.pht.service.IService;

import com.perennial.pht.model.Doctor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IdoctorService {

    public List<Doctor> getAllDoctors();

    public Doctor createRecord(Doctor doctor);

    public ResponseEntity<Doctor> getDoctorById(long id);

    public ResponseEntity<Doctor> updateDoctor(long id, Doctor doctorDetails);

    public ResponseEntity<HttpStatus> deleteDoctor(long id);

}
