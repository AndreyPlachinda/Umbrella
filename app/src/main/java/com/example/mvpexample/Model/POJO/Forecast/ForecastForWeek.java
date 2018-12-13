package com.example.mvpexample.Model.POJO.Forecast;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ForecastForWeek {
    @SerializedName("periods")
    private List<ForecastData> forecastList;

    public List<ForecastData> getForecastList() {
        return forecastList;
    }


}