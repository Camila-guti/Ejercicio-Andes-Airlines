package com.andesairlines.checkin.controller;



import com.andesairlines.checkin.dto.ApiResponseDTO;
import com.andesairlines.checkin.dto.FlightResponseDTO;
import com.andesairlines.checkin.service.CheckinService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/flights")
public class FlightController {

    private final CheckinService checkinService;

    public FlightController(CheckinService checkinService) {
        this.checkinService = checkinService;
    }

    @GetMapping("/{id}/passengers")
    public ApiResponseDTO<FlightResponseDTO> getPassengersByFlight(
            @PathVariable Long id) {

        return checkinService.getPassengersByFlight(id);
    }
}

