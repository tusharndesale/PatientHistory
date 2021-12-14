package com.perennial.pht.dao;

import com.perennial.pht.dao.daoInterfaces.IPrescriptionDao;
import com.perennial.pht.exception.ResourceNotFoundException;
import com.perennial.pht.model.Prescription;
import com.perennial.pht.repository.MedicineRepository;
import com.perennial.pht.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
@Repository
public class PrescriptionDao implements IPrescriptionDao {
    @Autowired
     public PrescriptionRepository prescriptionRepository;
    @Autowired
    public MedicineRepository medicineRepository;

    public Prescription createRecord(Prescription prescription) {
        prescription.setDate(LocalDate.now());
        medicineRepository.saveAll(prescription.getMedicine());
        return prescriptionRepository.save(prescription);
    }

    public ResponseEntity<Prescription> getPrescription(Integer id) {
        Prescription prescription = prescriptionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prescription Does not Exist With id "+id));
        return ResponseEntity.ok(prescription);
    }
}
