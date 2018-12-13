package com.example.mvpexample.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.example.mvpexample.Model.DataManager;
import com.example.mvpexample.Model.POJO.City;
import com.example.mvpexample.Model.POJO.CitySearch.CitySearchItem;
import com.example.mvpexample.Model.RetrofitClient;
import com.example.mvpexample.Presenter.SearchActivityPresenter;
import com.example.mvpexample.R;
import com.example.mvpexample.SearchActivityContract;

import java.util.List;

public class SearchActivity extends AppCompatActivity  implements SearchActivityContract.View, OnItemClickListener {
    private SearchView searchView;
    private SearchActivityContract.Presenter presenter;
    private RecyclerView mRecyclerView;
    private SearchAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_search);

        presenter = new SearchActivityPresenter(new DataManager(RetrofitClient.getApiService()));
        presenter.attachView(this);
        searchView = findViewById(R.id.search_field);
        searchView.onActionViewExpanded();
        mRecyclerView = findViewById(R.id.recycler_view_search); // use this setting to improve performance if you know that changes
        mRecyclerView.setHasFixedSize(true);                    // in content do not change the layout size of the RecyclerView
        mLayoutManager = new LinearLayoutManager(this); // use a linear layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new SearchAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                presenter.onSearch(s);
                return false;
            }
        });
    }

    @Override
    public void showCitiesList(List<CitySearchItem> citySearchItemList) {
        mAdapter.setCitiesList(citySearchItemList);
    }

    @Override
    public void onItemClick(City city) {
        presenter.addToDb(city);
        onBackPressed();
    }

    @Override
    public void showServerError() {
        Toast.makeText(this, "Server error!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInternetError() {
        Toast.makeText(this, "Internet error!", Toast.LENGTH_SHORT).show();
    }
}
