package com.alinaji.trigger.service;

import com.alinaji.trigger.model.Customer;
import com.alinaji.trigger.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alinaji.trigger.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CustomerService customerService;

    @Autowired
    public ProductService(ProductRepository productRepository, CustomerService customerService) {
        this.productRepository = productRepository;
        this.customerService = customerService;
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Product with ID " + id + " not found.");
        }
    }
    public void buyProduct(Long customerId, Long productId) {
        Customer customer = customerService.getCustomerById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer with ID " + customerId + " not found."));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + productId + " not found."));

        // Calculate the product price and check if the customer has enough credit
        double productPrice = product.getPrice();
        double customerCredit = customer.getCredit();

        if (customerCredit < productPrice) {
            throw new IllegalArgumentException("Insufficient credit to buy the product.");
        }

        // Deduct the product price from the customer's credit
        customer.setCredit(customerCredit - productPrice);

        // Add the product to the customer's boughtProducts list and set the customer field in the product
        customer.getBoughtProducts().add(product);
        product.setCustomer(customer);

        // Save the updated Customer and Product entities
        customerService.saveCustomer(customer);
        // You might also want to save the updated Product entity here if you have changes other than the customer field.

        // Return a success message or any other relevant response to indicate a successful purchase.
    }
}
