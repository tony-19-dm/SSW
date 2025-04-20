package com.example.CustomerWitchAutorisation.dto;

import com.example.CustomerWitchAutorisation.model.value.Address;
import lombok.Data;

@Data
public class Register {
    private String name;
    private Address address;
    private String username;
    private String password;
}