package com.abhinav.crs.dto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhinav.crs.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
