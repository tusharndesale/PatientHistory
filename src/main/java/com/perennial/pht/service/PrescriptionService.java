package com.perennial.pht.service;

import com.perennial.pht.dao.PrescriptionDao;
import com.perennial.pht.model.Prescription;
import com.perennial.pht.service.serviceInterfaces.IprescriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class PrescriptionService implements IprescriptionService {
    public static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

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
