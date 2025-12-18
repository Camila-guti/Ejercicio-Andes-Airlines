package com.andesairlines.checkin.service.impl;

import com.andesairlines.checkin.dto.*;
import com.andesairlines.checkin.entity.*;
import com.andesairlines.checkin.repository.*;
import com.andesairlines.checkin.service.CheckinService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CheckinServiceImpl implements CheckinService {

    private final FlightRepository flightRepository;
    private final BoardingPassRepository boardingPassRepository;
    private final PassengerRepository passengerRepository;
    private final SeatRepository seatRepository;

    public CheckinServiceImpl(
            FlightRepository flightRepository,
            BoardingPassRepository boardingPassRepository,
            PassengerRepository passengerRepository,
            SeatRepository seatRepository
    ) {
        this.flightRepository = flightRepository;
        this.boardingPassRepository = boardingPassRepository;
        this.passengerRepository = passengerRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    public ApiResponseDTO<FlightResponseDTO> getPassengersByFlight(Long flightId) {

        Flight flight = flightRepository.findById(flightId).orElse(null);

        if (flight == null) {
            return new ApiResponseDTO<>(404, new FlightResponseDTO());
        }

        List<BoardingPass> boardingPasses =
                boardingPassRepository.findByFlightId(flightId);

        Map<Long, List<BoardingPass>> byPurchase =
                boardingPasses.stream()
                        .collect(HashMap::new,
                                (map, bp) -> map
                                        .computeIfAbsent(bp.getPurchaseId(), k -> new ArrayList<>())
                                        .add(bp),
                                HashMap::putAll);

        FlightResponseDTO flightDTO = new FlightResponseDTO();
        flightDTO.setFlightId(flight.getFlightId());
        flightDTO.setTakeoffDateTime(flight.getTakeoffDateTime());
        flightDTO.setTakeoffAirport(flight.getTakeoffAirport());
        flightDTO.setLandingDateTime(flight.getLandingDateTime());
        flightDTO.setLandingAirport(flight.getLandingAirport());
        flightDTO.setAirplaneId(flight.getAirplaneId());

        List<PassengerResponseDTO> passengersDTO = new ArrayList<>();

        for (List<BoardingPass> purchaseGroup : byPurchase.values()) {
            for (BoardingPass bp : purchaseGroup) {

                Passenger passenger =
                        passengerRepository.findById(bp.getPassengerId()).orElse(null);

                if (passenger == null) continue;

                PassengerResponseDTO dto = new PassengerResponseDTO();
                dto.setPassengerId(passenger.getPassengerId());
                dto.setDni(passenger.getDni());
                dto.setName(passenger.getName());
                dto.setAge(passenger.getAge());
                dto.setCountry(passenger.getCountry());

                dto.setBoardingPassId(bp.getBoardingPassId());
                dto.setPurchaseId(bp.getPurchaseId());
                dto.setSeatTypeId(bp.getSeatTypeId());
                dto.setSeatId(null); // se asigna despues

                passengersDTO.add(dto);
            }
        }

        flightDTO.setPassengers(passengersDTO);

        return new ApiResponseDTO<>(200, flightDTO);
    }
}
