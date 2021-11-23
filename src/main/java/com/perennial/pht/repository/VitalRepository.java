package com.perennial.pht.repository;

import com.perennial.pht.model.Vitals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

public interface VitalRepository extends JpaRepository<Vitals, Long> {
}
