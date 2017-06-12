package com.mihailenko.ilya.weatherforecastapp.data.repositories.places;

import com.mihailenko.ilya.weatherforecastapp.data.models.places.Place;
import com.mihailenko.ilya.weatherforecastapp.network.places.GooglePlacesApi;

import rx.Observable;

/**
 * Created by Ilya on 12.06.2017.
 */

public class GooglePlaceRepository implements IGooglePlaceRepository {

    private final GooglePlacesApi placesApi;

    public GooglePlaceRepository(GooglePlacesApi placesApi) {
        this.placesApi = placesApi;
    }

    @Override
    public Observable<Place> getSuggestions(String input) {
        return placesApi.getCitiesSuggestions(input);
    }
}
