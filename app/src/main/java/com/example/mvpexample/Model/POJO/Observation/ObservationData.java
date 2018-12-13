package com.example.mvpexample.Model.POJO.Observation;

import com.google.gson.annotations.SerializedName;

public class ObservationData {
    @SerializedName("tempC")
    private String temp;
    @SerializedName("icon")
    private String icon;

    public String getTemp() {
        return temp;
    }

    public String getIcon() {
        return icon;
    }
}
