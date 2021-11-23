package com.perennial.pht.repository;

import com.perennial.pht.model.Patient;
import com.perennial.pht.model.Vitals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}

