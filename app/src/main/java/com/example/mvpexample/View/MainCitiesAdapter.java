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
import com.example.mvpexample.Model.POJO.City;
import com.example.mvpexample.R;

import java.util.ArrayList;
import java.util.List;

public class MainCitiesAdapter extends RecyclerView.Adapter<MainCitiesAdapter.ViewHolder> {
    private List<City> citiesList;
    private OnItemClickListener onItemClickListener;
    private Context context;

    public void setList(List<City> list) {
        this.citiesList = list;
        notifyDataSetChanged();
    }

    public void resetList() {
        this.citiesList = citiesList.subList(0, 1);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView city;
        private TextView country;
        private TextView temp;
        private ImageView icon;

        public ViewHolder(View v) {
            super(v);
            city = v.findViewById(R.id.city);
            country = v.findViewById(R.id.country);
            temp = v.findViewById(R.id.temp);
            icon = v.findViewById(R.id.icon);
        }
    }

    public MainCitiesAdapter(Context context) {
        citiesList = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public MainCitiesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cities_recycler_view_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final City item = citiesList.get(position);
        holder.city.setText(item.getName());
        holder.country.setText(item.getCountry());
        holder.temp.setText(item.getTemp());
        Glide.with(context).load(item.getIcon()).into(holder.icon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }
}
