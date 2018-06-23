package android.coolweather.com.util;

import android.coolweather.com.db.City;
import android.coolweather.com.db.County;
import android.coolweather.com.db.Province;
import android.coolweather.com.gson.Weather;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hbhdw on 2018/6/19.
 */

/*
* 解析和处理返回的省级数据
* */
public class Utility {
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            JSONArray allProvice = null;
            try {
                if(response.startsWith("\ufeff")){
                    response = response.substring(1);
                }
                Log.d("wxfff", response);
                allProvice = new JSONArray(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < allProvice.length(); i++) {
                try {
                    JSONObject proviceObject = allProvice.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(proviceObject.getString("name"));
                    province.setProvinceCode(proviceObject.getInt("id"));
                    province.save();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return true;

        }
        return false;
    }


    /*
    * 解析和处理服务器返回的市级数据
    *
    * */
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {

            try {
                if(response.startsWith("\ufeff")){
                    response = response.substring(1);
                }
                Log.d("wxfff", response);
                JSONArray allcities = new JSONArray(response);

                for (int i = 0; i < allcities.length(); i++) {
                    JSONObject cityObject = allcities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
    *   解析和处理服务器返回的县级数据
    *
     */
    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)){
            try {
                if(response.startsWith("\ufeff")){
                    response = response.substring(1);
                }
                Log.d("wxfff", response);
                JSONArray allCountries = new JSONArray(response);
                for (int i = 0; i < allCountries.length(); i++) {
                    JSONObject countyObject = allCountries.getJSONObject(i);
                    County county = new County();
                    county.setCountryName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    /*
    * 将返回的JSON数据解析成Weather实体类
    * */

    public  static Weather handleWeatherResponse(String response){

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }
}
