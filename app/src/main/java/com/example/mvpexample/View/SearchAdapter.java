package com.example.mvpexample.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvpexample.Model.POJO.City;
import com.example.mvpexample.Model.POJO.CitySearch.CitySearchItem;
import com.example.mvpexample.R;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    private List<CitySearchItem> citySearchItemList;
    private com.example.mvpexample.View.OnItemClickListener OnItemClickListener;

    public void setOnItemClickListener(com.example.mvpexample.View.OnItemClickListener onItemClickListener) {
        OnItemClickListener = onItemClickListener;
    }

    public void setCitiesList (List<CitySearchItem> citySearchItemList) {
        this.citySearchItemList = citySearchItemList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.cityName);
        }
    }

    public SearchAdapter() {
        citySearchItemList = new ArrayList<>();
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_recycler_view_item, parent, false);// create a new view
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder viewHolder, final int i) {
        final CitySearchItem item = citySearchItemList.get(i);
        viewHolder.mTextView.setText(item.getResponseData().getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                City city = new City();
                city.setName(item.getResponseData().getName());
                city.setCountry(item.getResponseData().getCountry());
                OnItemClickListener.onItemClick(city);
            }
        });
    }

    public int getItemCount() {
        return citySearchItemList.size();
    }
}
