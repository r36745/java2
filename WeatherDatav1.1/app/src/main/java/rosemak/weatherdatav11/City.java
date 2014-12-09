package rosemak.weatherdatav11;

import java.io.Serializable;

/**
 * Created by stevierose on 12/5/14.
 */
public class City implements Serializable{

    private static final long serialVersionUID = 12450459L;

    public String weather_name        = "";
    public String weather_type        = "";
    public String weather_description = "";


    public String getWeatherType() {return weather_type; }
    public String getWeatherDescription() {return weather_description;}
    public String getWeather_name() {return weather_name;}

    public void setWeather_type(String weather_type) {
        this.weather_type = weather_type;
    }

    public void setWeather_name(String weather_name) {
        this.weather_name = weather_name;
    }

    public void setWeather_description(String weather_description) {
        this.weather_description = weather_description;
    }

    @Override
    public String toString() {return weather_name;}

}
