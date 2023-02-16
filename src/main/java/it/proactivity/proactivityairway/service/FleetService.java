package it.proactivity.proactivityairway.service;

import it.proactivity.proactivityairway.builder.FleetBuilder;
import it.proactivity.proactivityairway.model.Fleet;
import it.proactivity.proactivityairway.model.dto.FleetDto;
import it.proactivity.proactivityairway.repository.FleetRepository;
import it.proactivity.proactivityairway.utility.FleetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FleetService {
    @Autowired
    FleetRepository fleetRepository;

    @Autowired
    FleetValidator fleetValidator;

    public ResponseEntity insertFleet(FleetDto fleetDto) {

        if (!fleetValidator.validateNameOfFleet(fleetDto.getAirplaneDescription())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (!fleetValidator.validateAvailability(fleetDto.getAvailability())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<Fleet> fleet = fleetRepository.findByAirplaneDescription(fleetDto.getAirplaneDescription());

        if (fleet.isPresent()) {
            fleet.get().setAvailability(fleetDto.getAvailability());
            fleetRepository.save(fleet.get());
            return ResponseEntity.ok().build();

        }else {

            if (!fleetValidator.validateAirplaneModel(fleetDto.getAirplaneDescription())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }else {
                Fleet newFleet = createFleet(fleetDto);
                fleetRepository.save(newFleet);
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        }
    }

    public ResponseEntity deleteFleetFromAirplaneModel(String airplaneModel) {
        if (!fleetValidator.validateNameOfFleet(airplaneModel)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<Fleet> fleet = fleetRepository.findByAirplaneDescription(airplaneModel);

        fleetRepository.delete(fleet.get());
        return ResponseEntity.ok().build();
    }

    private Fleet createFleet(FleetDto fleetDto) {
        if (fleetDto == null) {
            throw new IllegalArgumentException("Fleet dro can't be null");
        }
        if (fleetDto.getNumberOfSeat() == null) {
            throw new IllegalArgumentException("Number of seats can't be null");
        }
        Fleet fleet = FleetBuilder.newBuilder(fleetDto.getAirplaneDescription())
                .numberOfSeat(fleetDto.getNumberOfSeat())
                .availability(fleetDto.getAvailability())
                .build();
        return fleet;
    }
}
