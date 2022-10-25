package com.abhinav.crs.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long reservationId;

	@Temporal(TemporalType.DATE)
	private Date reservationStartDate;

	@Temporal(TemporalType.DATE)
	private Date reservationEndDate;

	private int reservationCost;

	@OneToOne
	@JoinColumn(name = "bookedVehicle", referencedColumnName = "id")
	private Vehicle bookedVehicle;

	@ManyToOne
	@JoinColumn(name = "bookedCustomer", referencedColumnName = "id")
	private Customer customer;

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public Date getReservationStartDate() {
		return reservationStartDate;
	}

	public void setReservationStartDate(Date reservationStartDate) {
		this.reservationStartDate = reservationStartDate;
	}

	public Date getReservationEndDate() {
		return reservationEndDate;
	}

	public void setReservationEndDate(Date reservationEndDate) {
		this.reservationEndDate = reservationEndDate;
	}

	public int getReservationCost() {
		return reservationCost;
	}

	public void setReservationCost(int reservationCost) {
		this.reservationCost = reservationCost;
	}

	public Vehicle getBookedVehicle() {
		return bookedVehicle;
	}

	public void setBookedVehicle(Vehicle bookedVehicle) {
		this.bookedVehicle = bookedVehicle;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
