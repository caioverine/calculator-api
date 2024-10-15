package com.challenge.calculator.controller;

import com.challenge.calculator.model.User;
import com.challenge.calculator.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/operations")
public class OperationController {

    @Autowired
    private OperationService operationService;

    private User getAuthenticatedUser() {
        Authentication authen = SecurityContextHolder.getContext().getAuthentication();

        Object jwt = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = jwt.getClaim("preferred_username");
//        String realm_access = jwt.getClaim("realm_access");

        return null;
    }

    @PostMapping("/addition")
    public ResponseEntity<Double> add(@RequestParam double a, @RequestParam double b) {
        return ResponseEntity.ok(operationService.add(a, b));
    }

    @PostMapping("/subtraction")
    public ResponseEntity<Double> subtract(@RequestParam double a, @RequestParam double b) {
        return ResponseEntity.ok(operationService.subtract(a, b));
    }

    @PostMapping("/multiplication")
    public ResponseEntity<Double> multiply(@RequestParam double a, @RequestParam double b) {
        return ResponseEntity.ok(operationService.multiply(a, b));
    }

    @PostMapping("/division")
    public ResponseEntity<Double> divide(@RequestParam double a, @RequestParam double b) {
        return ResponseEntity.ok(operationService.divide(a, b));
    }

    @PostMapping("/square-root")
    public ResponseEntity<Double> squareRoot(@RequestParam double a) {
        return ResponseEntity.ok(operationService.squareRoot(a));
    }
}
