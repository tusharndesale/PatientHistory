package com.perennial.pht.controller;

import com.perennial.pht.model.Doctor;
import com.perennial.pht.service.IService.IdoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

 //   @Autowired
  //  public DoctorService doctorService;
    @Autowired
    public IdoctorService service;

    @GetMapping("/getAll")
    public List<Doctor> getAllDoctors(){
        return service.getAllDoctors();
    }
    @PostMapping("/save")
    public Doctor createRecord(@RequestBody Doctor doctor){
     //   return service.createRecord(doctor);
        return service.createRecord(doctor);
    }

    @GetMapping("/findBy/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable long doctorId){
       // return service.getDoctorById(doctorId);
        return service.getDoctorById(doctorId);

    }

    @PutMapping("/updateById/{doctorId}")
    public  ResponseEntity<Doctor> updateDoctor(@PathVariable long doctorId, @RequestBody Doctor doctorDetails){
        return service.updateDoctor(doctorId,doctorDetails);

    }

    @DeleteMapping("/delete/{doctorId}")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable long doctorId){
        return service.deleteDoctor(doctorId);
    }

}
