package it.proactivity.proactivityairway.controller;

import it.proactivity.proactivityairway.service.FleetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FleetController {
    @Autowired
    FleetService fleetService;

    @PostMapping(value = "/insert-fleet")
    public ResponseEntity<?> insertNewFleet(@RequestBody String airplaneModel, Integer newAvailability) {
        return fleetService.insertNewFleet(airplaneModel, newAvailability);
    }

    @PostMapping(value = "/delete-fleet")
    public ResponseEntity<?> deleteAFlee(@RequestParam String airplaneModel) {
        return fleetService.deleteAFleet(airplaneModel);
    }
}
