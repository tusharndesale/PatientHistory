package com.perennial.pht.dao;

import com.perennial.pht.dao.daoInterfaces.IDoctorDao;
import com.perennial.pht.exception.ResourceNotFoundException;
import com.perennial.pht.model.Doctor;
import com.perennial.pht.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DoctorDao implements IDoctorDao {
    
  
        @Autowired
        private DoctorRepository doctorRepository;

        @Override
        public List<Doctor> getAllDoctors() {
            return doctorRepository.findAll();
        }

        @Override
        public Doctor createRecord(Doctor doctor) {
            return doctorRepository.save(doctor);
        }

        @Override
        public ResponseEntity<Doctor> getDoctorById(Integer id) {
            Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor Does not Exist With id "+id));
            return ResponseEntity.ok(doctor);
        }

        @Override
        public ResponseEntity<Doctor> updateDoctor(Integer id, Doctor doctorDetails) {
            Doctor updateDoctor = doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor Does not Exist With id "+id));
            updateDoctor.setName(doctorDetails.getName());
            updateDoctor.setGender(doctorDetails.getGender());
            updateDoctor.setAddress(doctorDetails.getAddress());
            updateDoctor.setMobileNo(doctorDetails.getMobileNo());
            updateDoctor.setSpecialization(doctorDetails.getSpecialization());
            updateDoctor.setDegree(doctorDetails.getDegree());
            doctorRepository.save(updateDoctor);
            return ResponseEntity.ok(updateDoctor);
        }

        @Override
        public ResponseEntity<HttpStatus> deleteDoctor(Integer id) {
            Doctor findDoctor = doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor Does not Exist With id "+id));
            doctorRepository.delete(findDoctor);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    @Override
    public List<String> addVitals(List<String> vitalList) {
        return null;
    }

    public Doctor testbyID(Integer id) {
            Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor Does not Exist With id "+id));
            return doctor;

        }
}
