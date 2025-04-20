package com.example.CustomerWitchAutorisation.model;

import com.example.CustomerWitchAutorisation.model.value.measurments.Weight;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Weight weight;

    private String description;

    @ElementCollection
    @CollectionTable(name = "order_details", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderDetail> orderDetails = new ArrayList<>();
}