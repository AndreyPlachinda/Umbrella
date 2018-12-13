package com.example.mvpexample.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpexample.ForecastActivityContract;
import com.example.mvpexample.Model.DataManager;
import com.example.mvpexample.Model.POJO.City;
import com.example.mvpexample.Model.POJO.Forecast.ForecastData;
import com.example.mvpexample.Model.RetrofitClient;
import com.example.mvpexample.Presenter.ForecastActivityPresenter;
import com.example.mvpexample.R;
import com.google.gson.Gson;

import java.util.List;

public class ForecastActivity extends AppCompatActivity implements ForecastActivityContract.View {

    private ForecastActivityContract.Presenter presenter;
    private RecyclerView oneDayView;
    private RecyclerView oneWeekView;
    private OneDayForecastAdapter oneDayForecastAdapter;
    private OneWeekForecastAdapter oneWeekForecastAdapter;
    private TextView cityName;
    private TextView cityTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        presenter = new ForecastActivityPresenter(new DataManager(RetrofitClient.getApiService()),
                new Gson().fromJson(getIntent().getStringExtra("city"), City.class));
        presenter.attachView(this);

        cityName = findViewById(R.id.name);
        cityTemp = findViewById(R.id.temp);

        oneDayView = findViewById(R.id.one_day_forecast_recycler_view);
        oneDayView.setHasFixedSize(true);
        oneDayView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        oneDayForecastAdapter = new OneDayForecastAdapter(this);
        oneDayView.setAdapter(oneDayForecastAdapter);

        oneWeekView = findViewById(R.id.one_week_forecast_recycler_view);
        oneWeekView.setHasFixedSize(true);
        oneWeekView.setLayoutManager(new LinearLayoutManager(this));
        oneWeekView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        oneWeekForecastAdapter = new OneWeekForecastAdapter(this);
        oneWeekView.setAdapter(oneWeekForecastAdapter);

        Button refreshBtn = findViewById(R.id.refresh_btn);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.refreshBtnClicked();
            }
        });

        presenter.loadData();
    }

    @Override
    public void displayBaseCityInfo(City city) {
        cityName.setText(city.getName());
        cityTemp.setText(city.getTemp());
    }

    @Override
    public void displayOneDayForecast(List<ForecastData> forecast) {
        oneDayForecastAdapter.setList(forecast);
    }

    @Override
    public void displayOneWeekForecast(List<ForecastData> forecast) {
        oneWeekForecastAdapter.setList(forecast);
    }

    @Override
    public void showUpdatedStatus() {
        Toast.makeText(this, "Data updated!", Toast.LENGTH_SHORT).show();
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