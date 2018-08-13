package com.mihailenko.ilya.weatherforecastapp.business.searchweather;

import com.mihailenko.ilya.weatherforecastapp.models.places.Place;
import com.mihailenko.ilya.weatherforecastapp.repositories.places.GooglePlaceRepository;

import java.util.List;

import io.reactivex.Single;


/**
 * Created by Ilya on 12.06.2017.
 */

public class GooglePlaceInteractorImpl implements GooglePlaceInteractor {
    private final GooglePlaceRepository googlePlaceRepository;

    public GooglePlaceInteractorImpl(GooglePlaceRepository googlePlaceRepository) {
        this.googlePlaceRepository = googlePlaceRepository;
    }

    @Override
    public Single<List<String>> findSuggestions(String input) {
        return googlePlaceRepository.getSuggestions(input)
                .map(Place::getSuggestions);
    }
}
