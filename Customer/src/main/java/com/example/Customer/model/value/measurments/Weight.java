package com.example.Customer.model.value.measurments;

import com.example.Customer.model.value.Measurment;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
public class Weight implements Measurment {
    private BigDecimal value;
    private String name;
    private String symbol;
}