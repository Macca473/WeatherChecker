package weatherchecker.springframework.weatherchecker.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import weatherchecker.springframework.weatherchecker.models.location;
import weatherchecker.springframework.weatherchecker.repositories.locationRepository;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class locationController {

    private final locationRepository LocationRepository;

    public locationController(locationRepository locationRepository) {
        LocationRepository = locationRepository;
    }

    @GetMapping("/AllLocations")
    List<location> allLocations() {
        return LocationRepository.findAll();
    }


    @GetMapping("/LocationsByUserName/{username}")
    List<location> getlocationbyusername(@PathVariable String username) {

        System.out.println("Finding by username: " + username);

        GetWeatherController GetWeatherController = new GetWeatherController();

        GetWeatherController.getEmployees("London");

        List<location> Location = LocationRepository.findByusernameContaining(username);



        return Location;
    }

    @PostMapping("/PostLocations")
    public ResponseEntity<location> createlocation(@RequestBody location _location) {

        location LocLocation = new location(
                _location.getusername(),
                new Date(),
                new Date(),
                _location.getCityName(),
                _location.getCityCode(),
                _location.getCountryName(),
                _location.getTemperature(),
                _location.getClouds()
        );

        _location = LocationRepository.save(LocLocation);

        LocationRepository.save(LocLocation);

        return new ResponseEntity<>(_location, HttpStatus.CREATED);
    }

}
