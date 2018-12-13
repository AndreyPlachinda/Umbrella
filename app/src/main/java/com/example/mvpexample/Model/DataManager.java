package com.example.mvpexample.Model;

import android.util.Log;

import com.example.mvpexample.App;
import com.example.mvpexample.Model.POJO.City;
import com.example.mvpexample.Model.POJO.CitySearch.CitySearchItem;
import com.example.mvpexample.Model.POJO.CitySearch.SearchResponseRoot;
import com.example.mvpexample.Model.POJO.Forecast.ForecastData;
import com.example.mvpexample.Model.POJO.Observation.ConditionObservation;
import com.example.mvpexample.Model.POJO.Forecast.ResponseForecast;
import com.example.mvpexample.Model.POJO.Observation.ObservationData;
import com.example.mvpexample.Presenter.DataCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataManager {

    private IApi api;

    public DataManager(IApi api) {
        this.api = api;
    }

    public void getConditions(String cityQuery, final DataCallback<ObservationData> callback) {
        api.getForecastForThisMoment(cityQuery, IApi.CLIENT_ID, IApi.CLIENT_SECRET).enqueue(new Callback<ConditionObservation>() {
            @Override
            public void onResponse(Call<ConditionObservation> call, Response<ConditionObservation> response) {
                callback.onResponse(response.body().getResponse().getObservationData());
            }

            @Override
            public void onFailure(Call<ConditionObservation> call, Throwable t) {
                if (t instanceof IOException) {
                    callback.onInternetError();
                } else {
                    callback.onServerError();
                }
            }
        });
    }

    public void getForecastForDay(String cityQuery, final DataCallback<List<ForecastData>> callback) {
        api.getForecastForDay(cityQuery, IApi.CLIENT_ID, IApi.CLIENT_SECRET).enqueue(new Callback<ResponseForecast>() {
            @Override
            public void onResponse(Call<ResponseForecast> call, Response<ResponseForecast> response) {
                callback.onResponse(response.body().getResponse().getForecastList());
            }

            @Override
            public void onFailure(Call<ResponseForecast> call, Throwable t) {
                if (t instanceof IOException) {
                    callback.onInternetError();
                } else {
                    callback.onServerError();
                }
            }
        });
    }

    public void getForecastForWeek(String cityQuery, final DataCallback<List<ForecastData>> callback) {
        api.getForecastForWeek(cityQuery, IApi.CLIENT_ID, IApi.CLIENT_SECRET).enqueue(new Callback<ResponseForecast>() {
            @Override
            public void onResponse(Call<ResponseForecast> call, Response<ResponseForecast> response) {
                callback.onResponse(response.body().getResponse().getForecastList());
            }

            @Override
            public void onFailure(Call<ResponseForecast> call, Throwable t) {
                if (t instanceof IOException) {
                    callback.onInternetError();
                } else {
                    callback.onServerError();
                }
            }
        });
    }

    public void getCitySearch(String searchingCity, final DataCallback<List<CitySearchItem>> callback) {
        api.getResponseCitySearch(searchingCity, IApi.CLIENT_ID, IApi.CLIENT_SECRET).enqueue(new Callback<SearchResponseRoot>() {
            @Override
            public void onResponse(Call<SearchResponseRoot> call, Response<SearchResponseRoot> response) {
                callback.onResponse(response.body().getResponseCitySearchList());
            }

            @Override
            public void onFailure(Call<SearchResponseRoot> call, Throwable t) {
                if (t instanceof IOException) {
                    callback.onInternetError();
                } else {
                    callback.onServerError();
                }
            }
        });
    }

    public List<City> readFromDb() {
        String defaultVal = "[{api:\"odessa,ukraine\",\"name\":\"Odessa\",\"country\":\"Ukraine\"}]";
        String jsonList = App.getSharedPrefrences().getString("cities", defaultVal);
        Type listType = new TypeToken<ArrayList<City>>() {
        }.getType();
        return new Gson().fromJson(jsonList, listType);
    }

    public void writeToDb(City city) {
        List<City> list = readFromDb();
        if (!isDuplicate(list, city))
            list.add(city);
        resetDb();
        App.getSharedPrefrences().edit().putString("cities", new Gson().toJson(list)).apply();
    }

    public void resetDb() {
        App.getSharedPrefrences().edit().clear().apply();
    }

    private boolean isDuplicate(List<City> list, City city) {
        for (City item : list) {
            if (item.getApi().equals(city.getApi())) {
                return true;
            }
        }
        return false;
    }
}
