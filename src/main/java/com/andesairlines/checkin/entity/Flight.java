package com.andesairlines.checkin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @Column(name = "flight_id")
    private Long flightId;

    @Column(name = "takeoff_date_time")
    private Long takeoffDateTime;

    @Column(name = "takeoff_airport")
    private String takeoffAirport;

    @Column(name = "landing_date_time")
    private Long landingDateTime;

    @Column(name = "landing_airport")
    private String landingAirport;

    @Column(name = "airplane_id")
    private Long airplaneId;
}
