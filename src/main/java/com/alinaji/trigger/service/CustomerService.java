package com.alinaji.trigger.service;

import com.alinaji.trigger.model.Customer;
import com.alinaji.trigger.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        try {
            return customerRepository.findById(id);
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Customer with ID " + id + " not found.");
        }
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        try {
            customerRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Customer with ID " + id + " not found.");
        }
    }
}