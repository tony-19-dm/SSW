package com.example.Customer.model.paymentType;

import com.example.Customer.model.Payment;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "credit_payments")
public class Credit extends Payment {
    private String number;
    private String type;
    private LocalDateTime expDate;
}