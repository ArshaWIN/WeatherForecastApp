package com.mihailenko.ilya.weatherforecastapp.business.searchweather;

import com.mihailenko.ilya.weatherforecastapp.data.models.places.Place;
import com.mihailenko.ilya.weatherforecastapp.data.repositories.places.IGooglePlaceRepository;

import java.util.List;

import rx.Observable;

/**
 * Created by Ilya on 12.06.2017.
 */

public class SearchWeatherInteractor implements ISearchWeatherInteractor {
    private final IGooglePlaceRepository googlePlaceRepository;

    public SearchWeatherInteractor(IGooglePlaceRepository googlePlaceRepository) {
        this.googlePlaceRepository = googlePlaceRepository;
    }

    @Override
    public Observable<List<String>> findSuggestions(String input) {
        return googlePlaceRepository.getSuggestions(input)
                .map(Place::getSuggestions);
    }
}
