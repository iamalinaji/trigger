package com.alinaji.trigger.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference // Use this annotation on the "many" side of the relationship
    private Customer customer;
}