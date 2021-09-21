package weatherchecker.springframework.weatherchecker.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import weatherchecker.springframework.weatherchecker.models.location;
import weatherchecker.springframework.weatherchecker.repositories.locationRepository;

import java.util.Calendar;
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

        GetWeatherController.updateWeather(Location);

        return Location;
    }

    @PostMapping("/PostLocations")
    public ResponseEntity<location> createlocation(@RequestBody location _location) {

        location LocLocation = new location(
                _location.getusername(),
                Calendar.getInstance(),
//                ConvToDate(_location.getreqdate()),
                _location.getreqdate(),
                _location.getCityName(),
                new String("CITYCODENULL"),
                _location.getCountryName(),
                new String("TEMPNULL"),
                new String("CLOUDSNULL")
        );

        _location = LocationRepository.save(LocLocation);

        LocationRepository.save(LocLocation);

        return new ResponseEntity<>(_location, HttpStatus.CREATED);
    }

//    private Calendar ConvToDate(String getreqdate) {
//
//    }

}
