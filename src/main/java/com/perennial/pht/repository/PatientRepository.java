package com.perennial.pht.repository;

import com.perennial.pht.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query(value = "FROM Patient p WHERE p.name = :name and p.mobileNo = :mobileNo", nativeQuery = true)
    List<Patient> existsByNameAndMobileNo(@Param("name") String name, @Param("mobileNo") long mobileNo);
}

