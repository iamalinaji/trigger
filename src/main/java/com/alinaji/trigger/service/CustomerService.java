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
    public Customer increaseCredit(Long id, double amount) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer with ID " + id + " not found."));

        customer.setCredit(customer.getCredit() + amount);
        return customerRepository.save(customer);
    }

    public Customer decreaseCredit(Long id, double amount) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer with ID " + id + " not found."));

        double newCredit = customer.getCredit() - amount;
        if (newCredit < 0) {
            throw new IllegalArgumentException("Insufficient credit for customer with ID " + id);
        }

        customer.setCredit(newCredit);
        return customerRepository.save(customer);
    }
}