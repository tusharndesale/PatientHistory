package com.perennial.pht.controller;

import com.perennial.pht.excel.PatientExcel;
import com.perennial.pht.excel.ResponseMessage;
import com.perennial.pht.model.Patient;
import com.perennial.pht.model.Vitals;
import com.perennial.pht.service.IService.IpatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
//
    @Autowired
    public IpatientService patientService;

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;
/*
    @GetMapping("/home")
    public String homePage(){
        return "Home";
    }*/

 /*   @GetMapping("/test/{id}")
    public Patient testbyID(@PathVariable long id){
        return patientService.testbyID(id);
    }
*/
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
   /* @PostMapping("/addvital/{patientId}")
    public Vitals addPatientVital(@PathVariable Integer patientId, @RequestBody Vitals vitalValues){
        vitalValues.setPatientId(patientId);
        return patientService.addPatientVital(vitalValues);

    }*/
    @GetMapping("/vital/{patientId}")
    public List<Vitals> getVitaldetails(@PathVariable Integer patientId){
       return patientService.getVitaldetails(patientId);

    }
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (PatientExcel.hasExcelFormat(file)) {
            try {
                patientService.uploadFile(file);
                //store File in
                Path storageLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
                Files.copy(file.getInputStream(), storageLocation, StandardCopyOption.REPLACE_EXISTING);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }




}