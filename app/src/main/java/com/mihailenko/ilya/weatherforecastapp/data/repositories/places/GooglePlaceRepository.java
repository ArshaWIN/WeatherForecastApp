package com.mihailenko.ilya.weatherforecastapp.data.repositories.places;

import com.mihailenko.ilya.weatherforecastapp.data.models.places.Place;

import io.reactivex.Single;

/**
 * Created by Ilya on 12.06.2017.
 */

public interface GooglePlaceRepository {
    Single<Place> getSuggestions(String input);
}
