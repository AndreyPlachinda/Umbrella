package com.example.mvpexample;

import com.example.mvpexample.Model.POJO.City;

import java.util.List;

public interface MainActivityContract {
    interface View {
        void displayCities(List<City> list);
        void showServerError();
        void showInternetError();
        void openSearchView();
        void clearCitiesOnView();
        void openForecastView(City city);
    }

    interface Presenter {
        void attachView(View view);
        void detachView();
        void cityClicked(City city);
        void refreshBtnClicked();
        void addBtnClicked();
        void resetBtnClicked();
        void loadCities();
    }
}
