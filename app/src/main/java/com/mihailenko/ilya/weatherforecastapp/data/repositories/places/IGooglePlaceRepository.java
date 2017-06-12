package com.mihailenko.ilya.weatherforecastapp.data.repositories.places;

import com.mihailenko.ilya.weatherforecastapp.data.models.places.Place;

import rx.Observable;

/**
 * Created by Ilya on 12.06.2017.
 */

public interface IGooglePlaceRepository {
    Observable<Place> getSuggestions(String input);
}
