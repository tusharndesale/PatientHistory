package com.perennial.pht.repository;

import com.perennial.pht.model.Vitals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VitalRepository extends JpaRepository<Vitals, Long> {
}
