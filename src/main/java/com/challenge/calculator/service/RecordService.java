package com.challenge.calculator.service;

import com.challenge.calculator.model.Operation;
import com.challenge.calculator.model.Record;
import com.challenge.calculator.model.User;
import com.challenge.calculator.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public void createRecord(Operation operation, User user, double result) {
        Record record = new Record();
        record.setOperation(operation);

        User mockUser = new User();
        mockUser.setId(1L);
        record.setUser(mockUser);

        record.setAmount(operation.getCost());
        record.setUserBalance(0.0);
        record.setOperationResponse(String.valueOf(result));
        record.setDate(LocalDateTime.now());
        record.setActive(true);
        recordRepository.save(record);
    }
}
