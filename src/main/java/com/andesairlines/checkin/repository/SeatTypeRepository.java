package com.andesairlines.checkin.repository;

import com.andesairlines.checkin.entity.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatTypeRepository extends JpaRepository<SeatType, Long> {
}
