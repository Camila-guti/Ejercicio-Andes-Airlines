package com.andesairlines.checkin.repository;

import com.andesairlines.checkin.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
