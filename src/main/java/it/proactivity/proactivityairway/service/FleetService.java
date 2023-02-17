package it.proactivity.proactivityairway.service;

import it.proactivity.proactivityairway.model.Fleet;
import it.proactivity.proactivityairway.model.dto.FleetDto;
import it.proactivity.proactivityairway.repository.FleetRepository;
import it.proactivity.proactivityairway.utility.FleetUtility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class FleetService {

    @Autowired
    FleetRepository fleetRepository;

    @Autowired
    FleetUtility fleetUtility;

    public ResponseEntity<?> insertNewFleet(String airplaneModel, Integer newAvailability) {
        if (StringUtils.isEmpty(airplaneModel)) {
            return ResponseEntity.badRequest().build();
        }
        if (newAvailability <= 0) {
            return ResponseEntity.badRequest().build();
        }
        Fleet fleet = fleetRepository.findByAirplaneDescription(airplaneModel);
        if (fleet == null) {
            if (fleetUtility.validateFleetName(airplaneModel)) {
                FleetDto fleetDto = new FleetDto(airplaneModel, newAvailability);
                Fleet newFleet = createNewFleet(fleetDto);
                fleetRepository.save(newFleet);
                return ResponseEntity.ok().build();
            }
        }
        fleet.setAvailability(fleet.getAvailability() + newAvailability);
        fleetRepository.save(fleet);
        return ResponseEntity.ok().build();
    }
    public ResponseEntity deleteAFleet(String airplaneModel) {
        if (airplaneModel.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Fleet fleet= fleetRepository.findByAirplaneDescription(airplaneModel);
        if (fleet != null) {
            fleet.setAvailability(fleet.getAvailability() - 1);
            fleetRepository.save(fleet);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    public Fleet createNewFleet(FleetDto fleetDto) {
        Fleet fleet = new Fleet();
        fleet.setAirplaneDescription(fleetDto.getAirplaneModel());
        fleet.setNumberOfSeat(fleetDto.getNumberOfSeat());
        fleet.setAvailability(fleetDto.getAvailability());
        fleet.setFlightList(fleetDto.getFlightList());
        return fleet;
    }


}
