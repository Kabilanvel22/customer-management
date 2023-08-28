package com.example.demo.service;

import org.springframework.http.HttpHeaders;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Customer;

@Service
public class ApiService {
  
	private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Customer> fetchAllCustomers(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Customer[]> response = restTemplate.exchange(
            "https://api.example.com/customers",
            HttpMethod.GET,
            entity,
            Customer[].class
        );

        return Arrays.asList(response.getBody());
    }

    public Customer createCustomer(Customer newCustomer, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Customer> entity = new HttpEntity<>(newCustomer, headers);

        ResponseEntity<Customer> response = restTemplate.exchange(
            "https://api.example.com/customers",
            HttpMethod.POST,
            entity,
            Customer.class
        );

        return response.getBody();
    }

    public void updateCustomer(String customerId, Customer updatedCustomer, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Customer> entity = new HttpEntity<>(updatedCustomer, headers);

        restTemplate.exchange(
            "https://api.example.com/customers/" + customerId,
            HttpMethod.PUT,
            entity,
            Void.class
        );
    }

    public void deleteCustomer(String customerId, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        restTemplate.exchange(
            "https://api.example.com/customers/" + customerId,
            HttpMethod.DELETE,
            entity,
            Void.class
        );
    } 
	
}
