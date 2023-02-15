package it.proactivity.proactivityairway.service;

import it.proactivity.proactivityairway.model.Fleet;
import it.proactivity.proactivityairway.repository.FleetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

// inserire un nuovo aereo nella flotta: il servizio prende in input una stringa "airplaneModel" e un numero "newAvailability",
// se il nome dell'aereo è già presente nel database allora viene aggiornata la colonna availability con il valore newAvailability,
// altrimenti restituisco una ResponseEntity 404 (Not Found). Se newAvailability è minore o uguale a 0 restituisco una ResponseEntity 400
// (Bad Request); la stringa "airplaneModel" non deve essere vuota (eccezione).

//- cancellare un aereo della flotta: il servizio prendere in input una stringa "airplaneModel" che non deve
// essere vuota e se trova il modello nel db fa la cancellazione altrimenti no e restituisce una
// ResponseEntity 400 (Bad Request)
@Service
public class FleetService {

    @Autowired
    FleetRepository fleetRepository;

    public ResponseEntity<?> insertNewFleet(String airplaneModel, Integer newAvailability) {
        if (airplaneModel == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (airplaneModel.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (newAvailability <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        List<String> modelList = fleetRepository.getFleetNames();
        if (modelList.contains(airplaneModel)) {
            Fleet fleet = fleetRepository.getFleetWhitAvailability(airplaneModel);
            fleet.setAvailability(fleet.getAvailability() + newAvailability);
            fleetRepository.save(fleet);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok().build();
    }


    public ResponseEntity<?> deleteAFleet(String airplaneModel) {
        if (airplaneModel.isEmpty()) {
            throw new IllegalArgumentException();
        }
        List<String> modelList = fleetRepository.getFleetNames();
        if (modelList.contains(airplaneModel)) {
           Fleet fleet = fleetRepository.getFleetWhitAvailability(airplaneModel);
           fleet.setAvailability(fleet.getAvailability() - 1);
           fleetRepository.save(fleet);
           return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


}
