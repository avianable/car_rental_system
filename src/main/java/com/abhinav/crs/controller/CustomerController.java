package com.abhinav.crs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhinav.crs.entity.Customer;
import com.abhinav.crs.exception.CustomerException;
import com.abhinav.crs.exception.InputPayloadException;
import com.abhinav.crs.service.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/save")
	public Customer addCustomer(@RequestBody Customer customer) throws InputPayloadException {
		return customerService.addCustomer(customer);
	}

	@GetMapping("/all")
	public List<Customer> getAllCustomers() throws CustomerException {
		return customerService.getAllCustomers();
	}

	@GetMapping("/detail/{id}")
	public Customer getCustomerById(@PathVariable("id") Long id) throws CustomerException {
		return customerService.getCustomerById(id);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteCustomer(@PathVariable("id") Long id) throws CustomerException {
		customerService.deleteCustomer(id);
	}

	@PutMapping("/update/{id}")
	public Customer updateCustomerDetails(@PathVariable Long id, @RequestBody Customer customer)
			throws CustomerException {

		customer.setId(id);
		return customerService.updateCustomer(id, customer);

	}
}
