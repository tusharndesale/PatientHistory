package com.perennial.pht.dao.daoInterfaces;

import com.perennial.pht.model.Prescription;
import org.springframework.http.ResponseEntity;

public interface IPrescriptionDao {
    Prescription createRecord(Prescription prescription);

    ResponseEntity<Prescription> getPrescription(Integer id);
}
