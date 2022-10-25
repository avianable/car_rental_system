package com.abhinav.crs.exception;

import java.util.List;

import com.abhinav.crs.entity.Vehicle;

public class VehicleException extends Exception {

	private static final long serialVersionUID = 8888435367687861212L;

	public VehicleException() {
		super();
	}

	public VehicleException(String message) {
		super(message);
	}

	public static boolean isVehicleListEmpty(List<Vehicle> vehicles) {

		if (vehicles.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean checkIfVehicleExists(List<Vehicle> vehicles, Long vehicleId) {
		boolean vehicleExists = vehicles.stream().filter(vehicle -> vehicle.getId() == vehicleId).findFirst()
				.isPresent();
		return vehicleExists;
	}
}
