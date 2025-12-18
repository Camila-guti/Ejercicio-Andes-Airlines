package com.andesairlines.checkin.repository;

//obt.datos pasajeros
import com.andesairlines.checkin.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
