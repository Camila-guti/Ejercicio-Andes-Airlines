package com.andesairlines.checkin.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FlightResponseDTO {

    private Long flightId;
    private Long takeoffDateTime;
    private String takeoffAirport;

    private Long landingDateTime;
    private String landingAirport;

    private Long airplaneId;

    private List<PassengerResponseDTO> passengers;
}
