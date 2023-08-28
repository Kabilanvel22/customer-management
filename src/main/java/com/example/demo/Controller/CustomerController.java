package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.CustomerRepository;
import com.example.demo.entity.Customer;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	  private final CustomerRepository customerRepository;

	    @Autowired
	    public CustomerController(CustomerRepository customerRepository) {
	        this.customerRepository = customerRepository;
	    }

	    @GetMapping
	    public List<Customer> getAllCustomers() {
	        return customerRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
	        Optional<Customer> customer = customerRepository.findById(id);
	        return customer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	    }

	    @PostMapping
	    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
	        Customer createdCustomer = customerRepository.save(customer);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
	        Optional<Customer> optionalCustomer = customerRepository.findById(id);
	        if (optionalCustomer.isPresent()) {
	            Customer existingCustomer = optionalCustomer.get();
	            existingCustomer.setFirstname(customerDetails.getFirstname());
	            existingCustomer.setLastname(customerDetails.getLastname());
	            existingCustomer.setStreet(customerDetails.getStreet());
	            existingCustomer.setAddress(customerDetails.getAddress());
	            existingCustomer.setState(customerDetails.getState());
	            existingCustomer.setPhone(customerDetails.getPhone());
	            existingCustomer.setCity(customerDetails.getCity());
	            existingCustomer.setEmail(customerDetails.getEmail());
	            
	            customerRepository.save(existingCustomer);
	            return ResponseEntity.ok(existingCustomer);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
	        Optional<Customer> optionalCustomer = customerRepository.findById(id);
	        if (optionalCustomer.isPresent()) {
	            customerRepository.delete(optionalCustomer.get());
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	
}
