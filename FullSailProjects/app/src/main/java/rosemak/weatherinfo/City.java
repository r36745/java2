package rosemak.weatherinfo;

import java.io.Serializable;

/**
 * Created by stevierose on 11/30/14.
 */
public class City implements Serializable{
    private static final long serialVersionUID = 12450459L;


    public String weather_type        = "";
    public String weather_description = "";

    public City ( String _weatherType, String _weatherDescription) {


        weather_type        = _weatherType;
        weather_description = _weatherDescription;
    }

    public String getWeatherTypeAndDesc() {return weather_type + "" + weather_description ;}
    public String getWeatherType() {return weather_type; }
    public String getWeatherDescription() {return weather_description;}

}
