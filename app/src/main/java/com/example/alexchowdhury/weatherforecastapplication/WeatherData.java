package com.example.alexchowdhury.weatherforecastapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by alexchowdhury on 10/29/17.
 */

public class WeatherData {
    public String date;
    public String currentTemp;
    public String highTemp;
    public String lowTemp;
    public String conditions;

    public WeatherData getWeatherData(JSONObject data) {

        JSONObject query = null;

        try {
            query = data.getJSONObject("query");
            JSONObject result = query.getJSONObject("results");
            JSONObject channel = result.getJSONObject("channel");
            JSONObject item = channel.getJSONObject("item");
            JSONArray forecast = item.getJSONArray("forecast");
            JSONObject currentForecast = forecast.getJSONObject(0);
            JSONObject condition = item.getJSONObject("condition");
            this.date = currentForecast.getString("date");
            this.currentTemp = condition.getString("temp");
            this.highTemp = currentForecast.getString("high");
            this.lowTemp = currentForecast.getString("low");
            this.conditions = currentForecast.getString("text");
            return this;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
