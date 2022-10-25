package com.abhinav.crs.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhinav.crs.dto.CustomerRepository;
import com.abhinav.crs.dto.ReservationRepository;
import com.abhinav.crs.dto.VehicleRepository;
import com.abhinav.crs.entity.Customer;
import com.abhinav.crs.entity.Reservation;
import com.abhinav.crs.entity.Vehicle;
import com.abhinav.crs.exception.CustomerException;
import com.abhinav.crs.exception.ReservationException;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public List<Reservation> getAllReservations() {
		List<Reservation> reservations = reservationRepository.findAll();
//		if (ReservationException.isReservationListEmpty(reservations)) {
//			throw new ReservationException("No reservations available. Please make one");
//		}
		return reservations;
	}

	public Reservation createReservation(Long vehicleId, Long customerId, Reservation reservation)
			throws ReservationException, CustomerException {

		Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
		if (!vehicle.isAvailable()) {
			throw new ReservationException("Vehicle is already booked.");
		}
		List<Customer> customers = customerRepository.findAll();
		Customer customer = customerRepository.findById(customerId).get();
		if (!CustomerException.checkIfCustomerExist(customers, customerId)) {
			throw new CustomerException("No customer found.");
		}
		Date reservationStartDate = reservation.getReservationStartDate();
		Date reservationEndDate = reservation.getReservationEndDate();
		if (ReservationException.validateDates(reservation)) {
			throw new ReservationException("Reservation start date cannot be after end date");
		}
		reservation.setReservationStartDate(reservationStartDate);
		reservation.setReservationEndDate(reservationEndDate);
		reservation.setBookedVehicle(vehicle);
		reservation.setCustomer(customer);

		int vehicleCostPerDay = vehicle.getRentalCostPerDay();
		long duration = reservationEndDate.getTime() - reservationStartDate.getTime();
		duration = TimeUnit.MILLISECONDS.toDays(duration);

		int newReservationCost = 0;
		if (duration == 0) {
			newReservationCost = reservation.getBookedVehicle().getRentalCostPerDay();
		} else {
			newReservationCost = (int) (vehicleCostPerDay * duration);
		}
		reservation.setReservationCost(newReservationCost);
		vehicle.setAvailable(false);
		vehicleRepository.save(vehicle);

		reservationRepository.save(reservation);
		return reservation;

	}

	public List<Reservation> viewCustomerReservations(Long customerId) throws ReservationException {
		Customer customer = customerRepository.findById(customerId).get();
		List<Reservation> reservations = reservationRepository.findAll();
		if (!ReservationException.checkCustomerReservationExist(reservations, customerId)) {
			throw new ReservationException("No Reservations found for the customer");
		}
		List<Reservation> retrievedReservations = reservations.stream()
				.filter(reservation -> reservation.getCustomer().equals(customer)).toList();
		return retrievedReservations;
	}

	public Reservation returnVehicle(Long vehicleId) throws ReservationException {
		List<Vehicle> vehicles = vehicleRepository.findAll();

		if (!ReservationException.checkIfVehicleExists(vehicles, vehicleId)) {
			throw new ReservationException("No details found !");
		}
		Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
		vehicle.setAvailable(true);
		vehicleRepository.save(vehicle);
		List<Reservation> reservations = getAllReservations();
		Reservation returnedReservationDetails = reservations.stream()
				.filter(reservation -> reservation.getBookedVehicle().getId().equals(vehicleId)).findAny().get();
		return returnedReservationDetails;
	}

	public void deleteReturnedVehicle(Long vehicleId) throws ReservationException {
		Vehicle vehicle = vehicleRepository.findById(vehicleId).get();

		List<Reservation> reservations = getAllReservations();
		if (vehicle.isAvailable()) {
			Reservation currentReservation = reservations.stream()
					.filter(reservation -> reservation.getBookedVehicle().equals(vehicle)).findFirst().get();
			reservationRepository.delete(currentReservation);
		} else {
			throw new ReservationException("The vehicle has not been returned !");
		}

	}
}
