package it.proactivity.proactivityairway.service;

import it.proactivity.proactivityairway.model.Fleet;
import it.proactivity.proactivityairway.model.dto.FleetDto;
import it.proactivity.proactivityairway.repository.FleetRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

        if(fleet.isPresent()) {
            fleet.get().setAvailability(fleetDto.getAvailability());
            fleetRepository.save(fleet.get());
        }
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
        return true;
    }

    private Boolean validateAvailability(Integer availability) {
        if (availability <= 0) {
            return false;
        }
        return true;
    }

}
