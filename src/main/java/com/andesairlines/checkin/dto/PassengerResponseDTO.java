package com.andesairlines.checkin.dto;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({
        "passengerId",
        "dni",
        "name",
        "age",
        "country",
        "boardingPassId",
        "purchaseId",
        "seatTypeId",
        "seatId"
})
public class PassengerResponseDTO {

    private Long passengerId;
    private Integer dni;
    private String name;
    private Integer age;
    private String country;
    private Long boardingPassId;
    private Long purchaseId;
    private Long seatTypeId;
    private Long seatId;
}
