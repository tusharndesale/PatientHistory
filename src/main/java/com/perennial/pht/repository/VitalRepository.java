package com.perennial.pht.repository;

import com.perennial.pht.model.Vitals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VitalRepository extends JpaRepository<Vitals, Long> {
}
