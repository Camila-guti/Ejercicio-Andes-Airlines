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

        try {
            // Buscar vuelo
            Flight flight = flightRepository.findById(flightId).orElse(null);
            if (flight == null) {
                return new ApiResponseDTO<>(404, new FlightResponseDTO());
            }

            // Boarding passes del vuelo
            List<BoardingPass> boardingPasses =
                    boardingPassRepository.findByFlightId(flightId);

            // Agrupar por compra
            Map<Long, List<BoardingPass>> byPurchase =
                    boardingPasses.stream()
                            .collect(HashMap::new,
                                    (map, bp) -> map
                                            .computeIfAbsent(bp.getPurchaseId(), k -> new ArrayList<>())
                                            .add(bp),
                                    HashMap::putAll);

            // DTO del vuelo
            FlightResponseDTO flightDTO = new FlightResponseDTO();
            flightDTO.setFlightId(flight.getFlightId());
            flightDTO.setTakeoffDateTime(flight.getTakeoffDateTime());
            flightDTO.setTakeoffAirport(flight.getTakeoffAirport());
            flightDTO.setLandingDateTime(flight.getLandingDateTime());
            flightDTO.setLandingAirport(flight.getLandingAirport());
            flightDTO.setAirplaneId(flight.getAirplaneId());

            List<PassengerResponseDTO> passengersDTO = new ArrayList<>();

            // Asientos ocupados globales
            Set<Long> occupiedSeatIds = new HashSet<>();

            // Procesar cada compra
            for (List<BoardingPass> purchaseGroup : byPurchase.values()) {

                List<Passenger> passengers = new ArrayList<>();

                for (BoardingPass bp : purchaseGroup) {
                    passengerRepository.findById(bp.getPassengerId())
                            .ifPresent(passengers::add);
                }

                // Separar adultos y menores
                List<Passenger> adults = new ArrayList<>();
                List<Passenger> minors = new ArrayList<>();

                for (Passenger p : passengers) {
                    if (p.getAge() < 18) {
                        minors.add(p);   // menor
                    } else {
                        adults.add(p);   // adulto
                    }
                }

                // Tipo de asiento
                Long seatTypeId = purchaseGroup.get(0).getSeatTypeId();

                // Asientos disponibles
                List<Seat> availableSeats =
                        seatRepository.findByAirplane_AirplaneIdAndSeatType_Id(
                                flight.getAirplaneId(),
                                seatTypeId
                        );

                // Eliminar ocupados
                availableSeats.removeIf(seat ->
                        occupiedSeatIds.contains(seat.getSeatId())
                );

                // Asignación pasajero → asiento
                Map<Long, Seat> assignedSeats = new HashMap<>();

                // Asignar adultos primero
                for (Passenger adult : adults) {
                    if (availableSeats.isEmpty()) break;

                    Seat seat = availableSeats.remove(0);
                    assignedSeats.put(adult.getPassengerId(), seat);
                    occupiedSeatIds.add(seat.getSeatId());
                }

                // Asignar menores junto a un adulto
                for (Passenger minor : minors) {
                    for (Seat adultSeat : assignedSeats.values()) {

                        Optional<Seat> adjacentSeat = availableSeats.stream()
                                .filter(seat ->
                                        seat.getSeatRow().equals(adultSeat.getSeatRow()) &&
                                        Math.abs(
                                                seat.getSeatColumn().charAt(0)
                                                        - adultSeat.getSeatColumn().charAt(0)
                                        ) == 1
                                )
                                .findFirst();

                        if (adjacentSeat.isPresent()) {
                            Seat seat = adjacentSeat.get();
                            assignedSeats.put(minor.getPassengerId(), seat);
                            occupiedSeatIds.add(seat.getSeatId());
                            availableSeats.remove(seat);
                            break;
                        }
                    }
                }

                // Crear DTO de pasajeros
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

                    Seat seat = assignedSeats.get(passenger.getPassengerId());
                    dto.setSeatId(seat != null ? seat.getSeatId() : null);

                    passengersDTO.add(dto);
                }
            }

            flightDTO.setPassengers(passengersDTO);
            return new ApiResponseDTO<>(200, flightDTO);

        } catch (Exception e) {
            return new ApiResponseDTO<>(400, "could not connect to db");
        }
    }
}
