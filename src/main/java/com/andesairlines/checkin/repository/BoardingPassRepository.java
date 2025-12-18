package com.andesairlines.checkin.repository;

import com.andesairlines.checkin.entity.BoardingPass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardingPassRepository extends JpaRepository<BoardingPass, Long> {

    List<BoardingPass> findByFlightId(Long flightId);
}
