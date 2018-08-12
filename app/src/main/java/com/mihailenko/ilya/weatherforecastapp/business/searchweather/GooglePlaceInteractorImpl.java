package com.mihailenko.ilya.weatherforecastapp.business.searchweather;

import com.mihailenko.ilya.weatherforecastapp.data.repositories.places.GooglePlaceRepository;

import java.util.ArrayList;
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

    private List<String> getCities() {
        List<String> strings = new ArrayList<>();
        strings.add("Krasnodar");
        strings.add("Moscow");
        strings.add("Ustka");

        return strings;
    }

    @Override
    public Single<List<String>> findSuggestions(String input) {
        return Single.just(getCities());
//        return googlePlaceRepository.getSuggestions(input)
//                .map(Place::getSuggestions);
    }
}
