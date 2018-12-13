package com.example.mvpexample.Model.POJO.Forecast;

import com.google.gson.annotations.SerializedName;

public class ForecastData {

    @SerializedName("avgTempC")
    private String averageTempC;
    @SerializedName("icon")
    private String icon;
    @SerializedName("dateTimeISO")
    private String dateTime;

    public String getAverageTempC() {
        return averageTempC;
    }

    public String getIcon() {
        return icon;
    }

    public String getDateTime() {
        return dateTime;
    }
}
