package it.proactivity.proactivityairway.controller;

import it.proactivity.proactivityairway.service.FleetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FleetController {
    @Autowired
    FleetService fleetService;

    @PostMapping(value = "insert")
    public ResponseEntity<?> insertNewFleet(@RequestParam String airplaneModel, @RequestParam Integer newAvailability) {
        return fleetService.insertNewFleet(airplaneModel, newAvailability);
    }

    @PostMapping(value = "delete")
    public ResponseEntity<?> deleteAFlee(@RequestParam String airplaneModel) {
        return fleetService.deleteAFleet(airplaneModel);
    }
}
