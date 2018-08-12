package com.mihailenko.ilya.weatherforecastapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.ui.view.searchweather.OnCitySearchListener;

import java.util.Collections;
import java.util.List;


public class CitiesAdapter extends ArrayAdapter<String> {

    private List<String> cities;
    private final OnCitySearchListener onCitySearchListener;

    public CitiesAdapter(Context context, OnCitySearchListener onCitySearchListener) {
        super(context, R.layout.item_city);
        this.onCitySearchListener = onCitySearchListener;
        cities = Collections.emptyList();
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return cities.get(position);
    }

    public void updateCities(List<String> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                if (null != charSequence && onCitySearchListener != null) {
                    onCitySearchListener.onCitySearch(charSequence.toString());
                }
                return null;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            }
        };
    }

}