package com.mihailenko.ilya.weatherforecastapp.repositories.places;

import com.mihailenko.ilya.weatherforecastapp.models.places.Place;
import com.mihailenko.ilya.weatherforecastapp.network.places.GooglePlacesApi;

import io.reactivex.Single;

/**
 * Created by Ilya on 12.06.2017.
 */

public class GooglePlaceRepositoryImpl implements GooglePlaceRepository {

    private final GooglePlacesApi placesApi;

    public GooglePlaceRepositoryImpl(GooglePlacesApi placesApi) {
        this.placesApi = placesApi;
    }

    @Override
    public Single<Place> getSuggestions(String input) {
        return placesApi.getCitiesSuggestions(input);
    }
}
