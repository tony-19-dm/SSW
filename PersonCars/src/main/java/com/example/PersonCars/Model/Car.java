package com.example.PersonCars.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String manufactures;
    private Float velocity;

    @Column(name = "km_run")
    private Integer kmRun;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getVelocity() {
        return velocity;
    }

    public void setVelocity(Float velocity) {
        this.velocity = velocity;
    }

    public String getManufactures() {
        return manufactures;
    }

    public void setManufactures(String manufactures) {
        this.manufactures = manufactures;
    }

    public Integer getKmRun() {
        return kmRun;
    }

    public void setKmRun(Integer kmRun) {
        this.kmRun = kmRun;
    }
}
