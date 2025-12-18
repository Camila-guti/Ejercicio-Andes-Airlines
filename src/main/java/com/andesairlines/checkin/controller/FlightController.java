package com.andesairlines.checkin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @GetMapping("/{id}/passengers")
    public Map<String, Object> getPassengersByFlight(
            @PathVariable("id") Long id) {

        Map<String, Object> response = new HashMap<>();

        response.put("code", 200);
        response.put("data", "endpoint funcionando para flightId = " + id);

        return response;
    }
}
