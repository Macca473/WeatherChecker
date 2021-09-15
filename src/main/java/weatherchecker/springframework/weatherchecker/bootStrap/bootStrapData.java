package weatherchecker.springframework.weatherchecker.bootStrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import weatherchecker.springframework.weatherchecker.models.location;
import weatherchecker.springframework.weatherchecker.repositories.locationRepository;

import java.util.Date;

@Component
public class bootStrapData implements CommandLineRunner {

    private final locationRepository locationRepository;

    public bootStrapData(weatherchecker.springframework.weatherchecker.repositories.locationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        location London = new location("Mic", new Date(), new Date(),"london","LO","england","32","cloudy");

        locationRepository.save(London);
    }
}