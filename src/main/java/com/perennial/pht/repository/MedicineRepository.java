package com.perennial.pht.repository;

import com.perennial.pht.model.Advice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Advice, Integer> {
}
