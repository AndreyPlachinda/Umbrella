package com.example.mvpexample.Model.POJO.CitySearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityData {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("countryFull")
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
