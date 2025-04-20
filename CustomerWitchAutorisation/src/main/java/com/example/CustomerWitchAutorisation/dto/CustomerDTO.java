package com.example.CustomerWitchAutorisation.dto;

import com.example.CustomerWitchAutorisation.model.value.Address;
import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private Address address;
}