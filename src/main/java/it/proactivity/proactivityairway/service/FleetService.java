package it.proactivity.proactivityairway.service;

import it.proactivity.proactivityairway.model.Fleet;
import it.proactivity.proactivityairway.model.Flight;
import it.proactivity.proactivityairway.model.dto.FleetDto;
import it.proactivity.proactivityairway.repository.FleetRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FleetService {
    @Autowired
    FleetRepository fleetRepository;

    public ResponseEntity insertFleet(FleetDto fleetDto) {

        if (!validateNameOfFleet(fleetDto.getAirplaneDescription())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (!validateAvailability(fleetDto.getAvailability())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<Fleet> fleet = fleetRepository.findByAirplaneDescription(fleetDto.getAirplaneDescription());

        fleet.get().setAvailability(fleetDto.getAvailability());
        fleetRepository.save(fleet.get());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity deleteFleetFromAirplaneModel(String airplaneModel) {
        if (!validateNameOfFleet(airplaneModel)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<Fleet> fleet = fleetRepository.findByAirplaneDescription(airplaneModel);

        fleetRepository.delete(fleet.get());
        return ResponseEntity.ok().build();
    }

    private Boolean validateNameOfFleet(String airplaneModel) {
        if (StringUtils.isEmpty(airplaneModel)) {
            throw new IllegalArgumentException("airplaneModel can't be null");
        }
        Optional<Fleet> fleet = fleetRepository.findByAirplaneDescription(airplaneModel);
        if (fleet.isEmpty()) {
            return false;
        }
        return true;
    }

    private Boolean validateAvailability(Integer availability) {
        if (availability <= 0) {
            return false;
        }
        return true;
    }

}
