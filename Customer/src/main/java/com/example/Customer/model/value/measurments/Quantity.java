package com.example.Customer.model.value.measurments;

import com.example.Customer.model.value.Measurment;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quantity implements Measurment {
    private Integer value;
    private String name;
    private String symbol;
}