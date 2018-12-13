package com.example.mvpexample.Presenter;

import com.example.mvpexample.MainActivityContract;
import com.example.mvpexample.Model.DataManager;
import com.example.mvpexample.Model.POJO.City;
import com.example.mvpexample.Model.POJO.Observation.ObservationData;
import com.google.gson.Gson;

import java.util.List;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private MainActivityContract.View view;
    private DataManager dataManager;

    public MainActivityPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if (view != null) {
            view = null;
        }
    }

    @Override
    public void cityClicked(City city) {
        view.openForecastView(city);
    }

    @Override
    public void refreshBtnClicked() {
        loadCities();
    }

    @Override
    public void addBtnClicked() {
        view.openSearchView();
    }

    @Override
    public void resetBtnClicked() {
        dataManager.resetDb();
        view.clearCitiesOnView();
    }

    @Override
    public void loadCities() {
        final List<City> dbList = dataManager.readFromDb();
        for (final City city : dbList) {
            dataManager.getConditions(city.getApi(), new DataCallback<ObservationData>() {
                @Override
                public void onResponse(ObservationData data) {
                    city.setTemp(data.getTemp());
                    city.setIcon(data.getIcon());
                    view.displayCities(dbList);
                }

                @Override
                public void onServerError() {
                    view.displayCities(dbList);
                    view.showServerError();
                }

                @Override
                public void onInternetError() {
                    view.displayCities(dbList);
                    view.showInternetError();
                }
            });
        }
    }
}


