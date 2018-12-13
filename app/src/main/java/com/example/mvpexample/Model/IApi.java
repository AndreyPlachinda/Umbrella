package com.example.mvpexample.Model;

import com.example.mvpexample.Model.POJO.CitySearch.SearchResponseRoot;
import com.example.mvpexample.Model.POJO.Forecast.ResponseForecast;
import com.example.mvpexample.Model.POJO.Observation.ConditionObservation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IApi {
//    String CLIENT_ID = "KYfd6cwKoGef2gW8KmK1s";
//    String CLIENT_SECRET = "E9T7CXngFa5MFZ3ZEHsD2gujwvlJMeOh64NPFDaH";
    String CLIENT_ID = " xT8XVec1K9xDZcYGvFTB1";
    String CLIENT_SECRET = "DImuBwk2yzTQmxwVRkPhcOB0xZtXe9oewvDIN0Wl";


    @GET("observations/{city_name}")
    Call<ConditionObservation> getForecastForThisMoment(@Path("city_name") String cityName,
                                                        @Query("client_id") String clientId,
                                                        @Query("client_secret") String clientSecret);

    @GET("forecasts/{city_name}?filter=day&limit=7")
    Call<ResponseForecast> getForecastForWeek(@Path("city_name") String cityName,
                                              @Query("client_id") String clientId,
                                              @Query("client_secret") String clientSecret);

    @GET("forecasts/{city_name}?filter=1hr&limit=24")
    Call<ResponseForecast> getForecastForDay(@Path("city_name") String cityName,
                                             @Query("client_id") String clientId,
                                             @Query("client_secret") String clientSecret);

    @GET("places/search?limit=5")
    Call<SearchResponseRoot> getResponseCitySearch(@Query("query") String cityName,
                                                   @Query("client_id") String clientId,
                                                   @Query("client_secret") String clientSecret);


}
