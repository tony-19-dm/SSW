package com.example.CustomerWitchAutorisation.service;

import com.example.CustomerWitchAutorisation.model.Customer;
import com.example.CustomerWitchAutorisation.model.value.Address;
import com.example.CustomerWitchAutorisation.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Customer createCustomer(String name, Address address) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        return customerRepository.save(customer);
    }
}