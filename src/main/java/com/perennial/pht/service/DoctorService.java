package com.perennial.pht.service;

import com.perennial.pht.dao.DoctorDao;
import com.perennial.pht.model.Doctor;
import com.perennial.pht.service.IService.IdoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class DoctorService implements IdoctorService {

        @Autowired
        public DoctorDao doctorDao;

        public List<Doctor> getAllDoctors() {
            return doctorDao.getAllDoctors();
        }

        public Doctor createRecord(Doctor doctor) {
            return doctorDao.createRecord(doctor);
        }

        public ResponseEntity<Doctor> getDoctorById(long doctorId) {
            return doctorDao.getDoctorById(doctorId);
        }

        public ResponseEntity<Doctor> updateDoctor(long doctorId, Doctor doctorDetails) {
            return doctorDao.updateDoctor(doctorId, doctorDetails);
        }

        public ResponseEntity<HttpStatus> deleteDoctor(long doctorId) {
            return doctorDao.deleteDoctor(doctorId);
        }

    @Override
    public List<String> addVitals(List<String> vitalList) {
        return doctorDao.addVitals(vitalList);
    }


}
