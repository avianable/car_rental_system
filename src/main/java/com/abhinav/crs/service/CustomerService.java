package com.abhinav.crs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhinav.crs.dto.CustomerRepository;
import com.abhinav.crs.entity.Customer;
import com.abhinav.crs.exception.CustomerException;
import com.abhinav.crs.exception.InputPayloadException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer addCustomer(Customer customer) throws InputPayloadException {

		boolean isException = InputPayloadException.validateCustomerBody(customer);
		if (isException) {
			throw new InputPayloadException("Additional information required");
		}
		return customerRepository.save(customer);
	}

	public List<Customer> getAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
//		if (CustomerException.isCustomerListEmpty(customers)) {
//			throw new CustomerException("No customers in the records.");
//		}
		return customers;
	}

	public Customer getCustomerById(Long customerId) throws CustomerException {
		List<Customer> customers = customerRepository.findAll();
		if (!CustomerException.checkIfCustomerExist(customers, customerId)) {
			throw new CustomerException("No such customer exists");
		}
		return customerRepository.findById(customerId).get();
	}

	public void deleteCustomer(Long customerId) throws CustomerException {
		List<Customer> customers = customerRepository.findAll();
		if (!CustomerException.checkIfCustomerExist(customers, customerId)) {
			throw new CustomerException("No such customer exists.");
		}
		customerRepository.deleteById(customerId);
	}

	public Customer updateCustomer(Long customerId, Customer customer) throws CustomerException {
		List<Customer> customers = customerRepository.findAll();
		if (!CustomerException.checkIfCustomerExist(customers, customerId)) {
			throw new CustomerException("No such customer found.");
		}

		customerRepository.save(customer);
		return customer;
	}
}
