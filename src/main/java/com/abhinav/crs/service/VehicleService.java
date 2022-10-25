package com.abhinav.crs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhinav.crs.dto.VehicleRepository;
import com.abhinav.crs.entity.Vehicle;
import com.abhinav.crs.exception.InputPayloadException;
import com.abhinav.crs.exception.VehicleException;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	public Vehicle addVehicle(Vehicle vehicle) throws InputPayloadException {
		boolean isVehicleBodyException = InputPayloadException.validateVehicleBody(vehicle);
		boolean isVehicleCostException = InputPayloadException.validateVehicleCostPerDay(vehicle);
		boolean isVehicleAvailabilityException = InputPayloadException.validateVehicleAvailablity(vehicle);
		if (isVehicleBodyException) {
			throw new InputPayloadException("Input fields cannot be empty.");
		}
		if (isVehicleCostException) {
			throw new InputPayloadException("Car cost per day is invalid.");
		}
		if (isVehicleAvailabilityException) {
			throw new InputPayloadException("Vehicle is not available");
		}
		return vehicleRepository.save(vehicle);

	}

	public List<Vehicle> getAllVehicles() {
		List<Vehicle> vehicles = vehicleRepository.findAll();
//		if (VehicleException.isVehicleListEmpty(vehicles)) {
//			throw new VehicleException("No vehicles present.");
//		}
		return vehicles;
	}

	public void removeVehicle(Long vehicleId) throws VehicleException {
		if (!VehicleException.checkIfVehicleExists(getAllVehicles(), vehicleId)) {
			throw new VehicleException("Vehicle does not exist!");
		}
		vehicleRepository.deleteById(vehicleId);
		;
	}

	public List<Vehicle> getAvailableVehicles() throws VehicleException {
		List<Vehicle> vehicles = getAllVehicles();
		List<Vehicle> availableVehicles = vehicles.stream().filter(vehicle -> vehicle.isAvailable() == true).toList();
		if (VehicleException.isVehicleListEmpty(availableVehicles)) {
			throw new VehicleException("No vehicles are available.");
		}
		return availableVehicles;
	}
}
