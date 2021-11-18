package com.perennial.pht.controller;

import com.perennial.pht.model.Patient;
import com.perennial.pht.service.IService.IpatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    public IpatientService patientService;

    @GetMapping("/home")
    public String homePage(){
        return "Home";
    }

    @GetMapping("/test/{id}")
    public Patient testbyID(@PathVariable long id){
        return patientService.testbyID(id);
    }

    @GetMapping("/getAll")
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }
    @PostMapping("/save")
    public Patient createRecord(@RequestBody Patient patient){
        return patientService.createRecord(patient);
    }

    @GetMapping("/findBy/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable long patientId){
        return patientService.getPatientById(patientId);
    }

    @PutMapping("/updateById/{patientId}")
    public  ResponseEntity<Patient> updatePatient(@PathVariable long patientId, @RequestBody Patient patientDetails){
        return patientService.updatePatient(patientId,patientDetails);
       }

    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<HttpStatus> deletePatient(@PathVariable long patientId){
         return patientService.deletePatient(patientId);

    }
   /* @PostMapping("/delvitalbete/{patientId}")
    public Patient deletePatient(@PathVariable long patientId){
        return patientService.deletePatient(patientId);

    }*/



}