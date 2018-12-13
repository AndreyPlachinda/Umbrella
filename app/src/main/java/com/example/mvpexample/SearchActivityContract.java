package com.example.mvpexample;

import com.example.mvpexample.Model.POJO.City;
import com.example.mvpexample.Model.POJO.CitySearch.CitySearchItem;

import java.util.List;

public interface SearchActivityContract {
    interface View {
        void showCitiesList(List<CitySearchItem> citySearchItemList);
        void showServerError();
        void showInternetError();
    }

    interface Presenter {
        void attachView(View view);
        void detachView();
        void addToDb(City city);
        void onSearch(String city);
    }
}
