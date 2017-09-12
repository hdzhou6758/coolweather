package com.coolweather.android.util;

import android.text.TextUtils;
import android.util.Log;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhd6758 on 2017/9/11.
 */

public class Utility {

    /**
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try{
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0;i < allProvinces.length();i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response,int provinceId){
        if (!TextUtils.isEmpty(response)){
            try{
                Log.e("hdz",response);
                JSONArray allCities = new JSONArray(response);
                for (int i = 0;i < allCities.length();i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response,int cityId){
        if (!TextUtils.isEmpty(response)){
            try{
                Log.e("hdz","000");
                Log.e("hdz",response);
                JSONArray allCounties = new JSONArray(response);
                Log.e("hdz","001");
                for (int i = 0;i < allCounties.length();i++){
                    Log.e("hdz","002");
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    Log.e("hdz","003");
                    County county = new County();
                    Log.e("hdz","004");
                    county.setCountyName(countyObject.getString("name"));
                    Log.e("hdz","005");
                    county.setWeatherId(countyObject.getString("weather_id"));
                    Log.e("hdz","006");
                    county.setCityId(cityId);
                    Log.e("hdz","007");
                    county.save();
                    Log.e("hdz","008");
                }
                Log.e("hdz","009");
                return true;

            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
