package com.perennial.pht.repository;

import com.perennial.pht.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    boolean existsByNameAndMobileNo(String Name, Long MobileNo);
}

