package weatherchecker.springframework.weatherchecker.models;

import javax.persistence.*;
import java.util.Calendar;

import static java.lang.Integer.parseInt;

@Entity
@Table(name = "location")
public class location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private Calendar CurDate;
    private Calendar reqdate;
    private String CityName;
    private String CityCode;
    private String CountryName;
    private String Temperature;
    private String Clouds;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        username = username;
    }

    public Calendar getCurDate() {
        return CurDate;
    }

    public void setCurDate(Calendar curDate) { CurDate = curDate; }

    public Calendar getreqdateCal() {
        return reqdate;
    }

    public Calendar getreqdateStr(String _reqdate) {

        String[] datevalues = _reqdate.split("-", 2);

        Calendar Cal = Calendar.getInstance();

        Cal.set(Calendar.DATE, parseInt(datevalues[0]));

        Cal.set(Calendar.MONTH, parseInt(datevalues[1]));

        Cal.set(Calendar.YEAR, parseInt(datevalues[2]));

        return Cal;
    }

    public void setreqdateCal(Calendar _reqdate) {
        reqdate = _reqdate;
    }

    public void setreqdateStr(String _reqdate) {

        String[] datevalues = _reqdate.split("-", 2);

        Calendar Cal = Calendar.getInstance();

        Cal.set(Calendar.DATE, parseInt(datevalues[0]));

        Cal.set(Calendar.MONTH, parseInt(datevalues[1]));

        Cal.set(Calendar.YEAR, parseInt(datevalues[2]));

        reqdate = Cal;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String cityCode) {
        CityCode = cityCode;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getClouds() {
        return Clouds;
    }

    public void setClouds(String clouds) {
        Clouds = clouds;
    }

    @Override
    public String toString() {
        return "location{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", CurDate=" + CurDate +
                ", reqdate=" + reqdate +
                ", CityName='" + CityName + '\'' +
                ", CityCode='" + CityCode + '\'' +
                ", CountryName='" + CountryName + '\'' +
                ", Temperature='" + Temperature + '\'' +
                ", Clouds='" + Clouds + '\'' +
                '}';
    }


    public location() {
    }

    public location(String username, Calendar CurDate, Calendar reqdate, String CityName, String CityCode, String CountryName, String Temperature, String Clouds) {
        this.username = username;
        this.CurDate = CurDate;
        this.reqdate = reqdate;
        this.CityName = CityName;
        this.CityCode = CityCode;
        this.CountryName = CountryName;
        this.Temperature = Temperature;
        this.Clouds = Clouds;
    }

//    public class updateweather() {
//    }
//
//    public updateloc(Long id, String CountryName, String Temperature, String Clouds) {
//        this.id = id;
//        this.CountryName = CountryName;
//        this.Temperature = Temperature;
//        this.Clouds = Clouds;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        location location = (location) o;

        return id != null ? id.equals(location.id) : location.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
