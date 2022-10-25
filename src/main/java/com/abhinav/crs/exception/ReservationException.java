package com.abhinav.crs.exception;

import java.util.Date;
import java.util.List;

import com.abhinav.crs.entity.Reservation;
import com.abhinav.crs.entity.Vehicle;

public class ReservationException extends Exception {

	private static final long serialVersionUID = -5789561353212240200L;

	public ReservationException() {

	}

	public ReservationException(String s) {
		super(s);
	}

	public static boolean isVehicleAvailable(Reservation reservation) {

		if (reservation.getBookedVehicle().isAvailable() == false) {
			return false;
		}
		return true;

	}

	public static boolean isReservationListEmpty(List<Reservation> reservations) {

		if (reservations.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean checkIfVehicleExists(List<Vehicle> vehicles, Long vehicleId) {
		boolean isVehicleExist = vehicles.stream().filter(vehicle -> vehicle.getId().equals(vehicleId)).findFirst()
				.isPresent();
		return isVehicleExist;
	}

	public static boolean validateDates(Reservation reservation) {
		Date start = reservation.getReservationStartDate();
		Date end = reservation.getReservationEndDate();

		int res = start.compareTo(end);
		if (res < 0) {
			return false;
		}
		return true;
	}

	public static boolean checkCustomerReservationExist(List<Reservation> reservations, Long customerId) {
		boolean isExist = reservations.stream()
				.filter(reservation -> reservation.getCustomer().getId().equals(customerId)).findAny().isPresent();
		return isExist;
	}
}
