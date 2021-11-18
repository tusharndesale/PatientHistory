package com.perennial.pht.service;

import com.perennial.pht.DAL.DoctorDAL;
import com.perennial.pht.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class DoctorService {

        @Autowired
        public DoctorDAL doctorDAL;

        public List<Doctor> getAllDoctors() {
            return doctorDAL.getAllDoctors();
        }

        public Doctor createRecord(Doctor doctor) {
            return doctorDAL.createRecord(doctor);
        }

        public ResponseEntity<Doctor> getDoctorById(long doctorId) {
            return doctorDAL.getDoctorById(doctorId);
        }

        public ResponseEntity<Doctor> updateDoctor(long doctorId, Doctor doctorDetails) {
            return doctorDAL.updateDoctor(doctorId, doctorDetails);
        }

        public ResponseEntity<HttpStatus> deleteDoctor(long doctorId) {
            return doctorDAL.deleteDoctor(doctorId);
        }


}
