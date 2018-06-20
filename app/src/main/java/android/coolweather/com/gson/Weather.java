package android.coolweather.com.gson;

import java.util.List;

/**
 * Created by hbhdw on 2018/6/21.
 */

public class Weather {
    public String status;
    public Basic basic;
    public AQI aqi;
    public Now now;
    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;
}
