package com.example.mvpexample;

import com.example.mvpexample.Model.POJO.City;
import com.example.mvpexample.Model.POJO.Forecast.ForecastData;

import java.util.List;

public interface ForecastActivityContract {
    interface View {
        void showServerError();
        void showInternetError();
        void displayOneDayForecast(List<ForecastData> list);
        void displayOneWeekForecast(List<ForecastData> list);
        void displayBaseCityInfo(City city);
        void showUpdatedStatus();
    }

    interface Presenter {
        void attachView(View view);
        void detachView();
        void loadData();
        void refreshBtnClicked();
    }
}
