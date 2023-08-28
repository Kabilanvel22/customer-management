package com.example.demo.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Repository.CustomerRepository;
import com.example.demo.entity.Customer;

@Controller
public class WebController {
	
	private  CustomerRepository customerRepository;

	  @GetMapping("/login")
	    public String login() {
	        return "login";
	    }
	    
	    @GetMapping("/customers/new")
	    public String showCreateCustomerForm(Model model) {
	        model.addAttribute("customer", new Customer());
	        return "customer-form";
	    }
	    
	    @GetMapping("/customers")
	    public String showCustomerList(Model model) {
	        List<Customer> customers = customerRepository.findAll(); // Fetch customers from repository
	        model.addAttribute("customers", customers);
	        return "customer-list";
	    }
	
}
