package com.example.mvpexample.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvpexample.Model.POJO.Forecast.ForecastData;
import com.example.mvpexample.R;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OneDayForecastAdapter extends RecyclerView.Adapter<OneDayForecastAdapter.ViewHolder> {
    private List<ForecastData> forecastList;
    private Context context;

    public void setList(List<ForecastData> list) {
        this.forecastList = list;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView time;
        private TextView temp;
        private ImageView icon;

        public ViewHolder(View v) {
            super(v);
            time = v.findViewById(R.id.time);
            temp = v.findViewById(R.id.temp);
            icon = v.findViewById(R.id.icon);
        }
    }

    public OneDayForecastAdapter(Context context) {
        forecastList = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public OneDayForecastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_day_forecast_view_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OneDayForecastAdapter.ViewHolder holder, int i) {
        final ForecastData item = forecastList.get(i);
        DateTimeFormat.shortDate().withLocale(Locale.US);
        holder.time.setText(DateTime
                .parse(item.getDateTime())
                .toString(DateTimeFormat.shortTime().withLocale(Locale.US)));
        holder.temp.setText(String.valueOf(item.getAverageTempC() + "°"));
        Glide.with(context).load("https://cdn.aerisapi.com/wxicons/v2/" + item.getIcon()).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }
}

