package com.perennial.pht.service;

import com.perennial.pht.dao.PrescriptionDao;
import com.perennial.pht.model.Prescription;
import com.perennial.pht.service.serviceInterfaces.IprescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService implements IprescriptionService {
    @Autowired
    public PrescriptionDao prescriptionDAL;

    @Override
    public Prescription createRecord(Prescription prescription) {
        return prescriptionDAL.createRecord(prescription);
    }

    @Override
    public ResponseEntity<Prescription> getPrescription(Integer id) {
        return prescriptionDAL.getPrescription(id);
    }
}
