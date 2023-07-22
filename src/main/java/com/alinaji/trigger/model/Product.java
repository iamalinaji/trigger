package com.alinaji.trigger.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    // Add other fields, constructors, getters, and setters as needed
}