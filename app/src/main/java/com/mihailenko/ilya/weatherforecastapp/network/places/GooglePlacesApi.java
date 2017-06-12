package com.mihailenko.ilya.weatherforecastapp.network.places;

import com.mihailenko.ilya.weatherforecastapp.data.models.places.Place;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ilya on 12.06.2017.
 */

public interface GooglePlacesApi {
    @GET(GooglePlacesApiConstans.Route.PLACE)
    Observable<Place> getCitiesSuggestions(@Query("input") String city);
}
