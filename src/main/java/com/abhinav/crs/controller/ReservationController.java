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

import com.abhinav.crs.entity.Reservation;
import com.abhinav.crs.exception.CustomerException;
import com.abhinav.crs.exception.ReservationException;
import com.abhinav.crs.service.ReservationService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@PostMapping("/{vehicleId}/add/{customerId}")
	public Reservation createReservation(@PathVariable Long vehicleId, @PathVariable Long customerId,
			@RequestBody Reservation reservation) throws ReservationException, CustomerException {
		return reservationService.createReservation(vehicleId, customerId, reservation);
	}

	@GetMapping("/all")
	public List<Reservation> getAllReservations() throws ReservationException {
		return reservationService.getAllReservations();
	}

	@GetMapping("/{customerId}")
	public List<Reservation> getCustomerReservations(@PathVariable Long customerId) throws ReservationException {
		return reservationService.viewCustomerReservations(customerId);
	}

	@PutMapping("/return/{vehicleId}")
	public Reservation returnVehicle(@PathVariable Long vehicleId) throws ReservationException {
		return reservationService.returnVehicle(vehicleId);
	}

	@DeleteMapping("/delete/{vehicleId}")
	public void deleteReturnedVehicle(@PathVariable Long vehicleId) throws ReservationException {
		reservationService.deleteReturnedVehicle(vehicleId);
	}
}
