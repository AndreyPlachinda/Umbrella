package com.example.mvpexample.View;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mvpexample.MainActivityContract;
import com.example.mvpexample.Model.DataManager;
import com.example.mvpexample.Model.POJO.City;
import com.example.mvpexample.Model.RetrofitClient;
import com.example.mvpexample.Presenter.MainActivityPresenter;
import com.example.mvpexample.R;
import com.google.gson.Gson;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MainActivityContract.View, OnItemClickListener {

    private MainActivityContract.Presenter presenter;
    private RecyclerView recyclerView;
    private MainCitiesAdapter mainCitiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter(new DataManager(RetrofitClient.getApiService()));
        presenter.attachView(this);

        recyclerView = findViewById(R.id.citiesListView); // use this setting to improve performance if you know that changes
        recyclerView.setHasFixedSize(true);                    // in content do not change the layout size of the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mainCitiesAdapter = new MainCitiesAdapter(this);
        mainCitiesAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mainCitiesAdapter);

        Button refreshBtn = findViewById(R.id.refresh_btn);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.refreshBtnClicked();
            }
        });

        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addBtnClicked();
            }
        });

        Button resetBtn = findViewById(R.id.reset_btn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.resetBtnClicked();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadCities();
    }

    @Override
    public void displayCities(List<City> list) {
        mainCitiesAdapter.setList(list);
        Toast.makeText(this, "Data updated!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(City city) {
        presenter.cityClicked(city);
    }

    @Override
    public void openSearchView() {
        startActivity(new Intent(this, SearchActivity.class));
    }

    @Override
    public void openForecastView(City city) {
        startActivity(new Intent(this, ForecastActivity.class)
                .putExtra("city", new Gson().toJson(city)));
    }

    @Override
    public void clearCitiesOnView() {
        mainCitiesAdapter.resetList();
    }

    @Override
    public void showServerError() {
        Toast.makeText(this, "Server error!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInternetError() {
        Toast.makeText(this, "Internet error!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
