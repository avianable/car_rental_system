package com.abhinav.crs.dto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhinav.crs.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
