package weatherchecker.springframework.weatherchecker.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import weatherchecker.springframework.weatherchecker.models.WeatherAPI;
import weatherchecker.springframework.weatherchecker.models.location;

import java.util.Date;
import java.util.List;

public class GetWeatherController {

    public Object updateWeather(List<location> _Location) {

        for (location ThisLoc:_Location) {
            WeatherAPI.Root API = getEmployees(ThisLoc.getCityName());

            String ReqDate = ThisLoc.getreqdate().toString().substring(0,9);
            //gotta find day of the year?
            ThisLoc.setTemperature("test");
            ThisLoc.setClouds();
            ThisLoc.setCountryName();
        }
    }

    public WeatherAPI.Root getEmployees(String cityname) {

        String apiKey = "1566020c66651f6712fd865503112dec";

        String uri = "https://api.openweathermap.org/data/2.5/forecast?q=" + cityname + "&mode=json&units=metric&appid=" + apiKey + "";

        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(uri, String.class);

        ObjectMapper om = new ObjectMapper();

        WeatherAPI.Root API = null;
        try {
            API = new ObjectMapper().readValue(jsonString, WeatherAPI.Root.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return API;
    }
}
