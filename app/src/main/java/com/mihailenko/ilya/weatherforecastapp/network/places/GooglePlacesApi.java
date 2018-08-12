package com.mihailenko.ilya.weatherforecastapp.network.places;

import com.mihailenko.ilya.weatherforecastapp.data.models.places.Place;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ilya on 12.06.2017.
 */

public interface GooglePlacesApi {
    @GET(GooglePlacesApiConstans.Route.PLACE)
    Single<Place> getCitiesSuggestions(@Query("input") String city);
}
