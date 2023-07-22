package com.alinaji.trigger.model;


import lombok.Data;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private double credit; // New credit field


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Product> boughtProducts = new ArrayList<>();
    // Add other fields, constructors, getters, and setters as needed
}
