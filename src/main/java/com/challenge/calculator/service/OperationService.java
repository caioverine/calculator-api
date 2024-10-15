package com.challenge.calculator.service;

import com.challenge.calculator.exception.InsufficientBalanceException;
import com.challenge.calculator.model.Operation;
import com.challenge.calculator.model.OperationTypeEnum;
import com.challenge.calculator.model.Record;
import com.challenge.calculator.model.User;
import com.challenge.calculator.repository.OperationRepository;
import com.challenge.calculator.repository.RecordRepository;
import com.challenge.calculator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private RecordService recordService;

    public double add(double a, double b) {
        return runOperation(OperationTypeEnum.ADDITION.name(), a, b);
    }

    public double subtract(double a, double b) {
        return runOperation(OperationTypeEnum.SUBTRACTION.name(), a, b);
    }

    public double multiply(double a, double b) {
        return runOperation(OperationTypeEnum.MULTIPLICATION.name(), a, b);
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero!");
        }

        return runOperation(OperationTypeEnum.DIVISION.name(), a, b);
    }

    public double squareRoot(double a) {
        if (a < 0) {
            throw new IllegalArgumentException("Cannot take the square root of a negative number!");
        }

        return runOperation(OperationTypeEnum.SQUARE_ROOT.name(), a, 0.0);
    }

    @Transactional
    public double runOperation(String operationType, double a, double b) {
        // TODO IMPLEMENTAR FLUXO DE USUÁRIO RECUPERADO DO KEYCLOAK!

        Operation operation = operationRepository.findByType(operationType)
                .orElseThrow(() -> new IllegalArgumentException("Invalid operation type"));

//        if (user.getBalance() < operation.getCost()) {
//            throw new InsufficientBalanceException("Insufficient balance for the operation.");
//        }

        double result = switch (operationType) {
            case "ADDITION" -> a + b;
            case "SUBTRACTION" -> a - b;
            case "MULTIPLICATION" -> a * b;
            case "DIVISION" -> a / b;
            case "SQUARE_ROOT" -> Math.sqrt(a);
            default -> throw new IllegalArgumentException("Operation not supported.");
        };

//        user.setBalance(user.getBalance() - operation.getCost());
//        userRepository.save(user);
        recordService.createRecord(operation, null, result);

        return result;
    }
}
