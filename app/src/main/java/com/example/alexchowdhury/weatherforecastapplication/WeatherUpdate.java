package com.example.alexchowdhury.weatherforecastapplication;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by alexchowdhury on 10/25/17.
 */

public class WeatherUpdate extends AsyncTask<String, Void, String> {
    private MainActivity mainactivity;

    WeatherUpdate(MainActivity mainactivity)    {
        this.mainactivity = mainactivity;
    }

    @Override
    public String doInBackground(String... endpoint) {
        String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")", endpoint[0]);
        String website = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));
        HttpsURLConnection connection;
        URL url = null;
        try {
            url = new URL(website);
            connection = (HttpsURLConnection) url.openConnection();
            connection.connect();
            InputStream inputstream = connection.getInputStream();

            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));

            String line;

            StringBuffer data = new StringBuffer();

            while((line=bufferedreader.readLine()) != null) {
                data.append(line);
            }

            return data.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onPostExecute(String data)  {
        WeatherData weatherData = new WeatherData();
        try {
            JSONObject jsonData = new JSONObject(data);
            weatherData.getWeatherData(jsonData);
            mainactivity.temp.setText(weatherData.currentTemp);
            mainactivity.conditions.setText(weatherData.conditions);
            mainactivity.low.setText(weatherData.lowTemp);
            mainactivity.high.setText(weatherData.highTemp);
            mainactivity.date.setText(weatherData.date);
        } catch (JSONException e) {
            e.printStackTrace();
            mainactivity.temp.setText("failed");
        }

    }

}
