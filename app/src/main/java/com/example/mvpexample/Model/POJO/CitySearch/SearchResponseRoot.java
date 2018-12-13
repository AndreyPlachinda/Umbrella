package com.example.mvpexample.Model.POJO.CitySearch;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponseRoot {
    @SerializedName("response")
    private List<CitySearchItem> responseCitySearchList;

    public List<CitySearchItem> getResponseCitySearchList() {
        return responseCitySearchList;
    }

    public void setResponseCitySearchList(List<CitySearchItem> responseCitySearchList) {
        this.responseCitySearchList = responseCitySearchList;
    }
}

