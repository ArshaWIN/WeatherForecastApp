package com.mihailenko.ilya.weatherforecastapp.di.searchweather;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.adapter.CitiesAdapter;
import com.mihailenko.ilya.weatherforecastapp.adapter.ForecastAdapter;
import com.mihailenko.ilya.weatherforecastapp.business.currentweather.CurrentWeatherInteractorImpl;
import com.mihailenko.ilya.weatherforecastapp.business.currentweather.ICurrentWeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.business.searchweather.SearchWeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.business.searchweather.SearchWeatherInteractorImpl;
import com.mihailenko.ilya.weatherforecastapp.data.repositories.places.GooglePlaceRepositoryImpl;
import com.mihailenko.ilya.weatherforecastapp.data.repositories.places.IGooglePlaceRepository;
import com.mihailenko.ilya.weatherforecastapp.data.repositories.weather.IWeatherForecastRepository;
import com.mihailenko.ilya.weatherforecastapp.data.repositories.weather.WeatherForecastRepositoryImpl;
import com.mihailenko.ilya.weatherforecastapp.di.PerActivity;
import com.mihailenko.ilya.weatherforecastapp.network.places.GooglePlacesApi;
import com.mihailenko.ilya.weatherforecastapp.network.weather.WeatherApi;
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
    SearchWeatherPresenter provideSearchWeatherPresenter(ICurrentWeatherInteractor currentWeatherInteractor, SearchWeatherInteractor searchWeatherInteractor) {
        return new SearchWeatherPresenter(searchWeatherActivity, currentWeatherInteractor, searchWeatherInteractor);
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
    IWeatherForecastRepository provideWeatherRepository(WeatherApi weatherApi) {
        return new WeatherForecastRepositoryImpl(weatherApi);
    }

    @PerActivity
    @Provides
    ICurrentWeatherInteractor provideCurrentWeatherInteractor(IWeatherForecastRepository weatherRepository) {
        return new CurrentWeatherInteractorImpl(weatherRepository);
    }


    @PerActivity
    @Provides
    SearchWeatherInteractor provideSearchWeatherInteractor(IGooglePlaceRepository googlePlaceRepository) {
        return new SearchWeatherInteractorImpl(googlePlaceRepository);
    }


    @PerActivity
    @Provides
    IGooglePlaceRepository provideGooglePlaceRepository(GooglePlacesApi placesApi) {
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
