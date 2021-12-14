package com.perennial.pht.service;

import com.perennial.pht.dao.DoctorDao;
import com.perennial.pht.model.Doctor;
import com.perennial.pht.service.serviceInterfaces.IdoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.invoke.MethodHandles;
import java.util.List;
@Service
@Transactional
public class DoctorService implements IdoctorService {
    public static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

        @Autowired
        public DoctorDao doctorDao;

        public List<Doctor> getAllDoctors() {
            return doctorDao.getAllDoctors();
        }

        public Doctor createRecord(Doctor doctor) {
            return doctorDao.createRecord(doctor);
        }

        public ResponseEntity<Doctor> getDoctorById(Integer doctorId) {
            return doctorDao.getDoctorById(doctorId);
        }

        public ResponseEntity<Doctor> updateDoctor(Integer doctorId, Doctor doctorDetails) {
            return doctorDao.updateDoctor(doctorId, doctorDetails);
        }

        public ResponseEntity<HttpStatus> deleteDoctor(Integer doctorId) {
            return doctorDao.deleteDoctor(doctorId);
        }

    @Override
    public List<String> addVitals(List<String> vitalList) {
        return doctorDao.addVitals(vitalList);
    }


}
