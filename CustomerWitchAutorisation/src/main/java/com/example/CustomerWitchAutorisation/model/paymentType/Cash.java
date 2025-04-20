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
@Table(name = "cash_payments")
public class Cash extends Payment {
    @Column(name = "cash_tendered")
    private float cashTendered;
}