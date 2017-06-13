package com.mihailenko.ilya.weatherforecastapp.di.searchweather;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.adapter.CitiesAdapter;

import com.mihailenko.ilya.weatherforecastapp.adapter.ForecastAdapter;
import com.mihailenko.ilya.weatherforecastapp.business.currentweather.CurrentWeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.business.currentweather.ICurrentWeatherInteractor;

import com.mihailenko.ilya.weatherforecastapp.common.ToastMessageShower;
import com.mihailenko.ilya.weatherforecastapp.data.repositories.places.GooglePlaceRepository;
import com.mihailenko.ilya.weatherforecastapp.data.repositories.places.IGooglePlaceRepository;
import com.mihailenko.ilya.weatherforecastapp.data.repositories.weather.IWeatherForecastRepository;
import com.mihailenko.ilya.weatherforecastapp.data.repositories.weather.WeatherForecastForecastRepository;
import com.mihailenko.ilya.weatherforecastapp.di.PerActivity;
import com.mihailenko.ilya.weatherforecastapp.interfaces.MessageShower;
import com.mihailenko.ilya.weatherforecastapp.network.places.GooglePlacesApi;
import com.mihailenko.ilya.weatherforecastapp.network.weather.WeatherApi;
import com.mihailenko.ilya.weatherforecastapp.ui.presenter.searchweather.SearchWeatherPresenter;
import com.mihailenko.ilya.weatherforecastapp.ui.presenter.searchweather.SearchWeatherPresenterImpl;
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
    SearchWeatherPresenter provideSearchWeatherPresenter(ICurrentWeatherInteractor currentWeatherInteractor) {
        return new SearchWeatherPresenterImpl(searchWeatherActivity, currentWeatherInteractor);
    }

    @PerActivity
    @Provides
    CitiesAdapter provideCitiesAdapter(IGooglePlaceRepository googlePlaceRepository, MessageShower toastMessageShower) {
        return new CitiesAdapter(searchWeatherActivity, googlePlaceRepository, toastMessageShower);
    }

    @PerActivity
    @Provides
    ItemDivider provideItemDivider() {
        return new ItemDivider(searchWeatherActivity, R.drawable.item_divider);
    }

    @PerActivity
    @Provides
    IWeatherForecastRepository provideWeatherRepository(WeatherApi weatherApi) {
        return new WeatherForecastForecastRepository(weatherApi);
    }

    @PerActivity
    @Provides
    ICurrentWeatherInteractor provideCurrentWeatherInteractor(IWeatherForecastRepository weatherRepository) {
        return new CurrentWeatherInteractor(weatherRepository);
    }


    @PerActivity
    @Provides
    IGooglePlaceRepository provideGooglePlaceRepository(GooglePlacesApi placesApi) {
        return new GooglePlaceRepository(placesApi);
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
