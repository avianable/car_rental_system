package com.abhinav.crs.exception;

import com.abhinav.crs.entity.Customer;
import com.abhinav.crs.entity.Vehicle;

public class InputPayloadException extends Exception {

	private static final long serialVersionUID = -8788363643026626043L;

	public InputPayloadException(String s) {
		super(s);
	}

	public static boolean validateCustomerBody(Customer customer) {
		if (customer.getCustomerName().isEmpty() || customer.getPhoneNumber().isEmpty()
				|| customer.getDrivingLicense().isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean validateVehicleBody(Vehicle vehicle) {
		if (vehicle.getCompanyName().isEmpty() || vehicle.getModelName().isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean validateVehicleCostPerDay(Vehicle vehicle) {
		if (vehicle.getRentalCostPerDay() <= 0) {
			return true;
		}
		return false;
	}

	public static boolean validateVehicleAvailablity(Vehicle vehicle) {
		if (vehicle.isAvailable() == false) {
			return true;
		}
		return false;
	}
}
