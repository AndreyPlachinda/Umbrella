package com.example.mvpexample.Model.POJO.CitySearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityLocation {
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("long")
    @Expose
    private Double _long;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLong() {
        return _long;
    }

    public void setLong(Double _long) {
        this._long = _long;
    }
}
