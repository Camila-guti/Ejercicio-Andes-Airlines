package com.andesairlines.checkin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "boarding_pass")
public class BoardingPass {

    @Id
    @Column(name = "boarding_pass_id")
    private Long boardingPassId;

    @Column(name = "purchase_id")
    private Long purchaseId;

    @Column(name = "passenger_id")
    private Long passengerId;

    @Column(name = "flight_id")
    private Long flightId;

    @Column(name = "seat_type_id")
    private Long seatTypeId;

    @Column(name = "seat_id")
    private Long seatId;
}
