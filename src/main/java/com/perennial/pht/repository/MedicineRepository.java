package com.perennial.pht.repository;

import com.perennial.pht.model.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Advice, Integer> {
}
