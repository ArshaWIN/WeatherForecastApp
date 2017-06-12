package com.mihailenko.ilya.weatherforecastapp.business.searchweather;

import com.mihailenko.ilya.weatherforecastapp.data.repositories.places.IGooglePlaceRepository;

import java.util.List;

import rx.Observable;

/**
 * Created by Ilya on 12.06.2017.
 */

public interface ISearchWeatherInteractor {
    Observable<List<String>> findSuggestions(String input);

}
