package com.challenge.calculator.repository;

import com.challenge.calculator.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
