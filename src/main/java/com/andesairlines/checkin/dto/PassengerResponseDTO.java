package com.andesairlines.checkin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
