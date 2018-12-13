package com.example.mvpexample.Model.POJO.Forecast;

import java.util.List;

import com.example.mvpexample.Model.POJO.Forecast.ForecastForWeek;
import com.google.gson.annotations.SerializedName;

public class ResponseForecast {
    @SerializedName("response")
    private List<ForecastForWeek> response;

    public ForecastForWeek getResponse() {
        return response.get(0);
    }
}
