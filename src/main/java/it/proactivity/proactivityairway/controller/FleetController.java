package it.proactivity.proactivityairway.controller;

import it.proactivity.proactivityairway.model.dto.FleetDto;
import it.proactivity.proactivityairway.service.FleetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FleetController {

    @Autowired
    FleetService fleetService;

    @PostMapping("/insert-fleet")
    public ResponseEntity insertFleet(@RequestBody FleetDto fleetDto) {

        return fleetService.insertFleet(fleetDto);
    }

    @GetMapping(value = "delete-fleet")
    public ResponseEntity deleteFleet(@RequestParam String airplaneModel) {
        return fleetService.deleteFleetFromAirplaneModel(airplaneModel);
    }
}
