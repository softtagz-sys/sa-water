// src/main/java/kdg/be/water/service/CustomerService.java
package kdg.be.water.service;

import kdg.be.water.domain.customer.Customer;
import kdg.be.water.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomerById(UUID customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}