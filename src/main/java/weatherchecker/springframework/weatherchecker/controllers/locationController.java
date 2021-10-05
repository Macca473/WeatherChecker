package weatherchecker.springframework.weatherchecker.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import weatherchecker.springframework.weatherchecker.models.location;
import weatherchecker.springframework.weatherchecker.repositories.locationRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class locationController {

    private final locationRepository LocationRepository;

    public locationController(locationRepository locationRepository) {
        LocationRepository = locationRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/AllLocations")
    List<location> allLocations() {
        return LocationRepository.findAll();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/LocationsByUserName/{username}")
    List<location> getlocationbyusername(@PathVariable String username) {

        System.out.println("Finding by username: " + username);

        GetWeatherController GetWeatherController = new GetWeatherController();

        GetWeatherController.getWeather("London");

        List<location> Location = LocationRepository.findByusernameContaining(username);

        GetWeatherController.updateWeather(Location);

        return Location;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/PostLocations")
    public ResponseEntity<location> createlocation(@RequestBody location _location) {

        location LocLocation = new location(
                _location.getusername(),
                new String(GetCurrDateString()),
                _location.getreqdate(),
                _location.getCityName(),
                new String("CITYCODENULL"),
                _location.getCountryName(),
                new String("TEMPNULL"),
                new String("CLOUDSNULL")
        );

        _location = LocationRepository.save(LocLocation);

        LocationRepository.save(LocLocation);

        return new ResponseEntity<>(LocLocation, HttpStatus.CREATED);
    }

    private String GetCurrDateString() {
        System.out.println("Getting Current Date");

        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date).toString();
    }

}
