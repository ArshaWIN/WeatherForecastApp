package com.mihailenko.ilya.weatherforecastapp.business.searchweather;

import java.util.List;

import io.reactivex.Single;


/**
 * Created by Ilya on 12.06.2017.
 */

public interface GooglePlaceInteractor {
    Single<List<String>> findSuggestions(String input);

}
