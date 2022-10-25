package com.abhinav.crs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhinav.crs.entity.Vehicle;
import com.abhinav.crs.exception.InputPayloadException;
import com.abhinav.crs.exception.VehicleException;
import com.abhinav.crs.service.VehicleService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@PostMapping("/add")
	public Vehicle addVehicle(@RequestBody Vehicle vehicle) throws InputPayloadException {
		return vehicleService.addVehicle(vehicle);
	}

	@GetMapping("/all")
	public List<Vehicle> getAllVehicles() throws VehicleException {
		return vehicleService.getAllVehicles();
	}

	@DeleteMapping("/remove/{id}")
	public void removeVehicle(@PathVariable("id") Long id) throws VehicleException {
		vehicleService.removeVehicle(id);
	}

	@GetMapping("/available")
	public List<Vehicle> getAvailableVehicles() throws VehicleException {
		return vehicleService.getAvailableVehicles();
	}
}
