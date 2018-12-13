package com.example.mvpexample.Presenter;

import com.example.mvpexample.ForecastActivityContract;
import com.example.mvpexample.Model.DataManager;
import com.example.mvpexample.Model.POJO.City;
import com.example.mvpexample.Model.POJO.Forecast.ForecastData;
import com.example.mvpexample.Model.POJO.Observation.ObservationData;

import java.util.ArrayList;
import java.util.List;

public class ForecastActivityPresenter implements ForecastActivityContract.Presenter {

    private ForecastActivityContract.View view;
    private DataManager dataManager;
    private City city;

    public ForecastActivityPresenter(DataManager dataManager, City city) {
        this.dataManager = dataManager;
        this.city = city;
    }

    @Override
    public void attachView(ForecastActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if (view != null) {
            view = null;
        }
    }

    @Override
    public void loadData() {
        dataManager.getForecastForDay(city.getApi(), new DataCallback<List<ForecastData>>() {
            @Override
            public void onResponse(List<ForecastData> forecast) {
                view.displayOneDayForecast(forecast);
            }

            @Override
            public void onServerError() {
                view.showServerError();
                view.displayOneDayForecast(new ArrayList<ForecastData>());
            }

            @Override
            public void onInternetError() {
                view.showInternetError();
                view.displayOneDayForecast(new ArrayList<ForecastData>());
            }
        });

        dataManager.getForecastForWeek(city.getApi(), new DataCallback<List<ForecastData>>() {
            @Override
            public void onResponse(List<ForecastData> forecast) {
                view.displayOneWeekForecast(forecast);
            }

            @Override
            public void onServerError() {
                view.showServerError();
                view.displayOneWeekForecast(new ArrayList<ForecastData>());
            }

            @Override
            public void onInternetError() {
                view.showInternetError();
                view.displayOneWeekForecast(new ArrayList<ForecastData>());
            }
        });

        dataManager.getConditions(city.getApi(), new DataCallback<ObservationData>() {
            @Override
            public void onResponse(ObservationData data) {
                city.setTemp(data.getTemp());
                view.displayBaseCityInfo(city);
                view.showUpdatedStatus();
            }

            @Override
            public void onServerError() {
                view.showServerError();
                city.setTemp(null);
                view.displayBaseCityInfo(city);
            }

            @Override
            public void onInternetError() {
                view.showInternetError();
                city.setTemp(null);
                view.displayBaseCityInfo(city);
            }
        });
    }

    @Override
    public void refreshBtnClicked() {
        loadData();
    }
}
