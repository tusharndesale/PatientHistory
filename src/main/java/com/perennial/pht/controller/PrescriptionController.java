package com.perennial.pht.controller;

import com.perennial.pht.model.Prescription;
import com.perennial.pht.service.serviceInterfaces.IprescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {

public IprescriptionService prescriptionService;

    @PostMapping("/save")
    public Prescription createRecord(@RequestBody Prescription prescription){
        return prescriptionService.createRecord(prescription);
    }
    @GetMapping("/findBy/{id}")
    public ResponseEntity<Prescription> getPrescription(@PathVariable Integer id){
        return prescriptionService.getPrescription(id);
    }

    @GetMapping("/downloadpdf/{id}")
    public ResponseEntity<Prescription> downloadPrescription(@PathVariable Integer id){
        return prescriptionService.getPrescription(id);
    }

}
