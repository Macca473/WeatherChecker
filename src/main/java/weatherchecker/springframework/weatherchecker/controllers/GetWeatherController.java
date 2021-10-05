package weatherchecker.springframework.weatherchecker.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import weatherchecker.springframework.weatherchecker.models.WeatherAPI;
import weatherchecker.springframework.weatherchecker.models.location;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class GetWeatherController {

    public void updateWeather(List<location> _Location) {

        System.out.println("updating weather");

        for (location ThisLoc:_Location) {
            WeatherAPI.Root API = getWeather(ThisLoc.getCityName());

            System.out.println("CurrDate: " + ThisLoc.getCurDate() + "| ReqDate: " + ThisLoc.getreqdate());

            int DateDiff = DateDiffrence(ThisLoc.getCurDate(),ThisLoc.getreqdate());

            System.out.println("DateDiff: " + DateDiff);

            WeatherAPI.list Thisinfo = API.list[(DateDiff * 8) - 4];

            ThisLoc.setTemperature("" + Thisinfo.main.temp);
            ThisLoc.setClouds("" + Thisinfo.clouds.all);

            ThisLoc.setCountryName(API.city.country);
        }
    }

    public WeatherAPI.Root getWeather(String cityname) {

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

    public Calendar StringToCalendar(String StrDate) {

        String[] datevalues = StrDate.split("-", 3);

        Calendar Cal = Calendar.getInstance();

        Cal.set(Calendar.DAY_OF_MONTH, parseInt(datevalues[0]));

        Cal.set(Calendar.MONTH, parseInt(datevalues[1]));

        Cal.set(Calendar.YEAR, parseInt(datevalues[2]));

        return Cal;
    }

        public int DateDiffrence(String currDate, String reqDate)
    {
        long CurCAL = StringToCalendar(currDate).getTimeInMillis();

        long ReqCAL = StringToCalendar(reqDate).getTimeInMillis();

        long millDiff = ReqCAL - CurCAL;

        return (int)TimeUnit.MILLISECONDS.toDays(millDiff);
    }
}
