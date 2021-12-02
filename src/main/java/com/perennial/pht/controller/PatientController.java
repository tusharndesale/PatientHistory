package com.perennial.pht.controller;

import com.perennial.pht.excel.ExcelToList;
import com.perennial.pht.model.Patient;
import com.perennial.pht.model.Vitals;
import com.perennial.pht.service.serviceInterfaces.IpatientService;
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
@RequestMapping("/patients")
public class PatientController {
//
    @Autowired
    public IpatientService patientService;

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    @GetMapping("/getAll")
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @PostMapping("/save")
    public Patient createRecord(@RequestBody Patient patient){
        return patientService.createRecord(patient);
    }

    @GetMapping("/findBy/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Integer patientId){
        return patientService.getPatientById(patientId);
    }

    @PutMapping("/updateById/{patientId}")
    public  ResponseEntity<Patient> updatePatient(@PathVariable Integer patientId, @RequestBody Patient patientDetails){
        return patientService.updatePatient(patientId,patientDetails);
       }

    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<HttpStatus> deletePatient(@PathVariable Integer patientId){
         return patientService.deletePatient(patientId);

    }

    @GetMapping("/vital/{patientId}")
    public List<Vitals> getVitaldetails(@PathVariable Integer patientId){
       return patientService.getVitaldetails(patientId);

    }
    @PostMapping("/upload")
    public ResponseEntity<HttpStatus> uploadFile(@RequestParam("file") MultipartFile file) {
            long fileSize = file.getSize();
        if (ExcelToList.hasExcelFormat(file) && fileSize>9500l) {
            try {
                List<Patient> issueRecordList = patientService.uploadFile(file);
                if(issueRecordList.isEmpty()){
                Path storageLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
                Files.copy(file.getInputStream(), storageLocation, StandardCopyOption.REPLACE_EXISTING);

                return new ResponseEntity<>(HttpStatus.OK);
                }else {
                    //create excel file

                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}