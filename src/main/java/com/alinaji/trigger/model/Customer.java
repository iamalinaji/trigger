package com.alinaji.trigger.model;


import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    // Add other fields, constructors, getters, and setters as needed
}
