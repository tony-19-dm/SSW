package com.example.Customer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ElementCollection
    @CollectionTable(name = "order_details", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    public void setPayment(Payment payment) {
        this.payment = payment;
        payment.setOrder(this);
    }

    public void addOrderDetail(OrderDetail detail1) {
        orderDetails.add(detail1);
    }
}