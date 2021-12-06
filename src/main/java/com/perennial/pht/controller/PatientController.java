package com.perennial.pht.controller;

import com.perennial.pht.excel.ExcelToList;
import com.perennial.pht.model.Patient;
import com.perennial.pht.model.Vitals;
import com.perennial.pht.service.serviceInterfaces.IpatientService;
import com.perennial.pht.utilities.CommonUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
//
    @Autowired
    public IpatientService patientService;

    CommonUtility utility;

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
    public ResponseEntity<Resource> uploadFile(@RequestParam("file") MultipartFile file) {
            long fileSize = file.getSize();
        String filename = "IssueRecords.xlsx";
        if (ExcelToList.hasExcelFormat(file) && fileSize>9500l) {
            try {
                List<Patient> issueRecordList = patientService.uploadFile(file);
                issueRecordList.forEach(ele-> System.out.println(ele.toString()));
                if(issueRecordList.isEmpty()){
                Path storageLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
                Files.copy(file.getInputStream(), storageLocation, StandardCopyOption.REPLACE_EXISTING);

                return new ResponseEntity<>(HttpStatus.OK);
                }else {
                    List<String> headerList =new ArrayList<>();
                    headerList.add("id");
                    headerList.add("patientName");
                    headerList.add("mobileNo");
                    headerList.add("gender");
                    headerList.add("dateOfBirth");
                    headerList.add("address");
                    headerList.forEach(ele-> System.out.println(ele));
                    InputStreamResource downloadFile = new InputStreamResource(patientService.load(headerList,issueRecordList));


                    return ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                            .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                            .body(downloadFile);
                   }
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

}