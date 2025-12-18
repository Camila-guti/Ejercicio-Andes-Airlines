package com.andesairlines.checkin.repository;


//Obtiene asientos
import com.andesairlines.checkin.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByAirplane_AirplaneIdAndSeatTypeId(
            Long airplaneId,
            Long seatTypeId
    );
}
