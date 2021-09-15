package weatherchecker.springframework.weatherchecker.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;


public class WeatherAPI {

    @JsonDeserialize(as = Main.class)
    public class Main{
        public double temp;
        public double feels_like;
        public double temp_min;
        public double temp_max;
        public int pressure;
        public int sea_level;
        public int grnd_level;
        public int humidity;
        public double temp_kf;
    }

    @JsonDeserialize(as = Weather.class)
    public class Weather{
        public int id;
        public String main;
        public String description;
        public String icon;
    }

    @JsonDeserialize(as = Clouds.class)
    public class Clouds{
        public int all;
    }

    @JsonDeserialize(as = Wind.class)
    public class Wind{
        public double speed;
        public int deg;
        public double gust;
    }

    @JsonDeserialize(as = Sys.class)
    public class Sys{
        public String pod;
    }

    @JsonDeserialize(as = Rain.class)
    public class Rain{
        @JsonProperty("3h")
        public double _3h;
    }

    @JsonDeserialize(as = list.class)
    public class list{
        public int dt;
        public Main main;
        public List<Weather> weather;
        public Clouds clouds;
        public Wind wind;
        public int visibility;
        public double pop;
        public Sys sys;
        public String dt_txt;
        public Rain rain;
    }

    @JsonDeserialize(as = Coord.class)
    public class Coord{
        public double lat;
        public double lon;
    }

    @JsonDeserialize(as = City.class)
    public class City{
        public int id;
        public String name;
        public Coord coord;
        public String country;
        public int population;
        public int timezone;
        public int sunrise;
        public int sunset;
    }

//    @JsonRootName(value = "Root")
    @JsonDeserialize(as = Root.class)
    public class Root{
        public String cod;
        public int message;
        public int cnt;
        public List<list> list;
        public City city;
    }

}
