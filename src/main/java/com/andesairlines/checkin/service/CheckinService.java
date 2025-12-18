package com.andesairlines.checkin.service;

import com.andesairlines.checkin.dto.ApiResponseDTO;
import com.andesairlines.checkin.dto.FlightResponseDTO;

public interface CheckinService {

    ApiResponseDTO<FlightResponseDTO> getPassengersByFlight(Long flightId);
}
