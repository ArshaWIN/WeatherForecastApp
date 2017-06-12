package com.mihailenko.ilya.weatherforecastapp.di.searchweather;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mihailenko.ilya.weatherforecastapp.adapter.CitiesAdapter;
import com.mihailenko.ilya.weatherforecastapp.adapter.ForecastAdapter;
import com.mihailenko.ilya.weatherforecastapp.di.PerActivity;
import com.mihailenko.ilya.weatherforecastapp.network.places.GooglePlacesApi;
import com.mihailenko.ilya.weatherforecastapp.ui.presenter.searchweather.SearchWeatherPresenter;
import com.mihailenko.ilya.weatherforecastapp.ui.presenter.searchweather.SearchWeatherPresenterImpl;
import com.mihailenko.ilya.weatherforecastapp.ui.view.searchweather.SearchWeatherActivity;

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
    SearchWeatherPresenter provideSearchWeatherPresenter() {
        return new SearchWeatherPresenterImpl(searchWeatherActivity);
    }
    @PerActivity
    @Provides
    CitiesAdapter provideForecastAdapter(GooglePlacesApi placesApi) {
        return new CitiesAdapter(searchWeatherActivity, placesApi);
    }
    
}
