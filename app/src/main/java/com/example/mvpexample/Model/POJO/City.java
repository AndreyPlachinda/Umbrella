package com.example.mvpexample.Model.POJO;

public class City {
    private String api;
    private String name;
    private String country;
    private String temp;
    private String icon;

    public String getApi() {
        if (api != null) return api;
        else return name.toLowerCase() + "," + country.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getTemp() {
        if (temp == null) return "--";
        else return Math.round(Double.parseDouble(temp)) + "°C";
    }

    public String getIcon() {
        return "https://cdn.aerisapi.com/wxicons/v2/" + icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
