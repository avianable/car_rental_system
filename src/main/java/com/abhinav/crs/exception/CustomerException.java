package com.abhinav.crs.exception;

import java.util.List;

import com.abhinav.crs.entity.Customer;

public class CustomerException extends Exception {

	private static final long serialVersionUID = -362688962640929130L;

	public CustomerException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerException(String message) {
		super(message);
	}

	public static boolean isCustomerListEmpty(List<Customer> customers) {
		if (customers.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean checkIfCustomerExist(List<Customer> customers, Long customerId) {
		boolean customerExists = customers.stream().filter(customer -> customer.getId().equals(customerId)).findFirst()
				.isPresent();
		return customerExists;
	}
}
