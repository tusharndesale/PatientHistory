package com.perennial.pht.service.IService;

import com.perennial.pht.model.Prescription;
import org.springframework.http.ResponseEntity;

public interface IprescriptionService {

    Prescription createRecord(Prescription prescription);

    ResponseEntity<Prescription> getPrescription(Integer id);
}
