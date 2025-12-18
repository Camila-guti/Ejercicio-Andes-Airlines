package com.andesairlines.checkin.repository;

import com.andesairlines.checkin.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
