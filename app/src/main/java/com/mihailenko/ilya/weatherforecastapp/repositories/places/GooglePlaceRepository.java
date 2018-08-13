package com.mihailenko.ilya.weatherforecastapp.repositories.places;

import com.mihailenko.ilya.weatherforecastapp.models.places.Place;

import io.reactivex.Single;

/**
 * Created by Ilya on 12.06.2017.
 */

public interface GooglePlaceRepository {
    Single<Place> getSuggestions(String input);
}
