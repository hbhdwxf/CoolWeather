package android.coolweather.com.util;

import android.coolweather.com.db.City;
import android.coolweather.com.db.County;
import android.coolweather.com.db.Province;
import android.text.TextUtils;

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
                JSONArray allCountries = new JSONArray(response);
                for (int i = 0; i < allCountries.length(); i++) {
                    JSONObject countryObject = allCountries.getJSONObject(i);
                    County county = new County();
                    county.setCountryName(countryObject.getString("name"));
                    county.setWeatherId(countryObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
