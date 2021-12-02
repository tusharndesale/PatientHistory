package com.perennial.pht.controller;

import com.perennial.pht.model.Doctor;
import com.perennial.pht.service.serviceInterfaces.IdoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {


    public IdoctorService doctorService;

    @GetMapping("/getAll")
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }
    @PostMapping("/save")
    public Doctor createRecord(@RequestBody Doctor doctor){
        return doctorService.createRecord(doctor);
    }

    @GetMapping("/findBy/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Integer doctorId){
       // return service.getDoctorById(doctorId);
        return doctorService.getDoctorById(doctorId);

    }

    @PutMapping("/updateById/{doctorId}")
    public  ResponseEntity<Doctor> updateDoctor(@PathVariable Integer doctorId, @RequestBody Doctor doctorDetails){
        return doctorService.updateDoctor(doctorId,doctorDetails);

    }

    @DeleteMapping("/delete/{doctorId}")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable Integer doctorId){
        return doctorService.deleteDoctor(doctorId);
    }
    @PostMapping("/addVital")
    public List<String> addVitals(@RequestBody List<String> vitalList){
        return doctorService.addVitals(vitalList);
    }

}
