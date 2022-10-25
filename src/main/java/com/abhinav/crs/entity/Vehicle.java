package com.abhinav.crs.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "vehicles")
public class Vehicle {

	@Id
	@GeneratedValue
	private Long id;

	private VehicleType vehicleType;
	private String companyName;
	private String modelName;
	private int rentalCostPerDay;
	private boolean isAvailable;

	@OneToOne(mappedBy = "bookedVehicle")
	@JsonIgnore
	private Reservation reservationId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getRentalCostPerDay() {
		return rentalCostPerDay;
	}

	public void setRentalCostPerDay(int rentalCostPerDay) {
		this.rentalCostPerDay = rentalCostPerDay;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

}
