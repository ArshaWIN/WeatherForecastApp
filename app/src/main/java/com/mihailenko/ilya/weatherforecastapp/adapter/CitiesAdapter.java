package com.mihailenko.ilya.weatherforecastapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.Filter;


import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.data.models.places.Place;
import com.mihailenko.ilya.weatherforecastapp.data.repositories.places.IGooglePlaceRepository;

import com.mihailenko.ilya.weatherforecastapp.utils.rx.RxSchedulers;

import java.util.Collections;
import java.util.List;


import timber.log.Timber;


public class CitiesAdapter extends ArrayAdapter<String> {
    private List<String> cities;
    private final IGooglePlaceRepository placeRepository;


    public CitiesAdapter(Context context, IGooglePlaceRepository googlePlaceRepository) {
        super(context, R.layout.item_city);
        this.placeRepository = googlePlaceRepository;
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

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                if (null != charSequence) {
                    updateCities(charSequence.toString());
                }
                return null;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            }
        };
    }

    private void updateCities(String input) {
        placeRepository.getSuggestions(input)
                .map(Place::getSuggestions)
                .compose(RxSchedulers.getIOToMainTransformer())
                .subscribe(suggestions -> {
                    cities = suggestions;
                    notifyDataSetChanged();
                }, throwable -> Timber.e(throwable, "Can't load cities"));
    }

}