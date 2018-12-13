package com.example.mvpexample.Presenter;

import com.example.mvpexample.Model.DataManager;
import com.example.mvpexample.Model.POJO.City;
import com.example.mvpexample.Model.POJO.CitySearch.CitySearchItem;
import com.example.mvpexample.SearchActivityContract;

import java.util.List;

public class SearchActivityPresenter implements SearchActivityContract.Presenter {
    private SearchActivityContract.View view;
    private DataManager dataManager;

    public SearchActivityPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(SearchActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if (view != null) {
            view = null;
        }
    }

    @Override
    public void onSearch(String city) {
        dataManager.getCitySearch("name:^" + city, new DataCallback<List<CitySearchItem>>() {
            @Override
            public void onResponse(List<CitySearchItem> list) {
                view.showCitiesList(list);
            }

            @Override
            public void onServerError() {
                view.showServerError();
            }

            @Override
            public void onInternetError() {
                view.showInternetError();
            }
        });
    }

    @Override
    public void addToDb(City city) {
        dataManager.writeToDb(city);
    }
}
