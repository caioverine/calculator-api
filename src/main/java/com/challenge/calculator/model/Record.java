package com.challenge.calculator.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "operation_id")
    private Operation operation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double amount;
    private Double userBalance;
    private String operationResponse;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private Boolean active;
}
