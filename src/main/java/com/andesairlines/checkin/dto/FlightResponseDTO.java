package com.andesairlines.checkin.dto;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({
        "flightId",
        "takeoffDateTime",
        "takeoffAirport",
        "landingDateTime",
        "landingAirport",
        "airplaneId",
        "passengers"
})
public class FlightResponseDTO {

    private Long flightId;
    private Long takeoffDateTime;
    private String takeoffAirport;
    private Long landingDateTime;
    private String landingAirport;
    private Long airplaneId;
    private List<PassengerResponseDTO> passengers;
}
