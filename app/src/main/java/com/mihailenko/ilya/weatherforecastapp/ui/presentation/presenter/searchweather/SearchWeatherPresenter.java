package com.mihailenko.ilya.weatherforecastapp.ui.presentation.presenter.searchweather;

import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.business.currentweather.WeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.business.searchweather.GooglePlaceInteractor;
import com.mihailenko.ilya.weatherforecastapp.ui.presentation.presenter.base.BasePresenter;
import com.mihailenko.ilya.weatherforecastapp.ui.presentation.view.searchweather.SearchWeatherView;
import com.mihailenko.ilya.weatherforecastapp.utils.rx.RxSchedulers;

import java.util.Collections;

/**
 * Created by Ilya on 12.06.2017.
 */

public class SearchWeatherPresenter extends BasePresenter<SearchWeatherView> {

    private final WeatherInteractor currentWeatherInteractor;
    private final GooglePlaceInteractor googlePlaceInteractor;

    public SearchWeatherPresenter(SearchWeatherView view,
                                  WeatherInteractor currentWeatherInteractor,
                                  GooglePlaceInteractor googlePlaceInteractor) {
        super(view);
        this.currentWeatherInteractor = currentWeatherInteractor;
        this.googlePlaceInteractor = googlePlaceInteractor;
    }

    public void onCitySelected(String city) {
        addDisposable(currentWeatherInteractor.getForecast(city)
                .compose(RxSchedulers.applySingleAsync())
                .doOnSubscribe(any -> view.startProgress())
                .doFinally(view::finishProgress)
                .subscribe(view::showForecast, throwable -> {
                    view.showMessage(throwable);
                    view.setToolbarTittle(R.string.find_city);
                    view.showForecast(Collections.emptyList());
                }));
    }

    public void onSearchCitySuggestions(String input) {
        addDisposable(googlePlaceInteractor.findSuggestions(input)
                .compose(RxSchedulers.applySingleAsync())
                .subscribe(view::showCitySuggestions, view::showMessage)
        );
    }
}
