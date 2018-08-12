package com.mihailenko.ilya.weatherforecastapp.di.searchweather;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.adapter.CitiesAdapter;
import com.mihailenko.ilya.weatherforecastapp.adapter.ForecastAdapter;
import com.mihailenko.ilya.weatherforecastapp.business.currentweather.WeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.business.searchweather.GooglePlaceInteractor;
import com.mihailenko.ilya.weatherforecastapp.business.searchweather.GooglePlaceInteractorImpl;
import com.mihailenko.ilya.weatherforecastapp.data.repositories.places.GooglePlaceRepository;
import com.mihailenko.ilya.weatherforecastapp.data.repositories.places.GooglePlaceRepositoryImpl;
import com.mihailenko.ilya.weatherforecastapp.di.PerActivity;
import com.mihailenko.ilya.weatherforecastapp.network.places.GooglePlacesApi;
import com.mihailenko.ilya.weatherforecastapp.ui.presenter.searchweather.SearchWeatherPresenter;
import com.mihailenko.ilya.weatherforecastapp.ui.view.searchweather.SearchWeatherActivity;
import com.mihailenko.ilya.weatherforecastapp.widget.ItemDivider;

import java.util.Collections;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ilya on 12.06.2017.
 */
@Module
public class SearchWeatherModule {
    private SearchWeatherActivity searchWeatherActivity;

    public SearchWeatherModule(SearchWeatherActivity searchWeatherActivity) {
        this.searchWeatherActivity = searchWeatherActivity;
    }

    @PerActivity
    @Provides
    SearchWeatherPresenter provideSearchWeatherPresenter(WeatherInteractor currentWeatherInteractor, GooglePlaceInteractor googlePlaceInteractor) {
        return new SearchWeatherPresenter(searchWeatherActivity, currentWeatherInteractor, googlePlaceInteractor);
    }

    @PerActivity
    @Provides
    CitiesAdapter provideCitiesAdapter() {
        return new CitiesAdapter(searchWeatherActivity, searchWeatherActivity);
    }

    @PerActivity
    @Provides
    ItemDivider provideItemDivider() {
        return new ItemDivider(searchWeatherActivity, R.drawable.item_divider);
    }

    @PerActivity
    @Provides
    GooglePlaceInteractor provideSearchWeatherInteractor(GooglePlaceRepository googlePlaceRepository) {
        return new GooglePlaceInteractorImpl(googlePlaceRepository);
    }


    @PerActivity
    @Provides
    GooglePlaceRepository provideGooglePlaceRepository(GooglePlacesApi placesApi) {
        return new GooglePlaceRepositoryImpl(placesApi);
    }

    @PerActivity
    @Provides
    ForecastAdapter provideForecastAdapter() {
        return new ForecastAdapter(Collections.emptyList());
    }

    @PerActivity
    @Provides
    RecyclerView.LayoutManager provideLayoutManager() {
        return new LinearLayoutManager(searchWeatherActivity);
    }


}
