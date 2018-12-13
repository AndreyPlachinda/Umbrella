package com.example.mvpexample.Model.POJO.CitySearch;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CitySearchItem {
    @SerializedName("loc")
    private CityLocation responseLocation;
    @SerializedName("place")
    private CityData responseData;

    public CityLocation getResponseLocation() {
        return responseLocation;
    }

    public void setResponseLocation(CityLocation responseLocation) {
        this.responseLocation = responseLocation;
    }

    public CityData getResponseData() {
        return responseData;
    }

    public void setResponseData(CityData responseData) {
        this.responseData = responseData;
    }
}
