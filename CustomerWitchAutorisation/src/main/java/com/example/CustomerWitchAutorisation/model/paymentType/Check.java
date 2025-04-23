package com.example.CustomerWitchAutorisation.model.paymentType;

import com.example.CustomerWitchAutorisation.model.Payment;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "check_payments")
public class Check extends Payment {
    private String name;

    @Column(name = "bank_id")
    private String bankID;
}
