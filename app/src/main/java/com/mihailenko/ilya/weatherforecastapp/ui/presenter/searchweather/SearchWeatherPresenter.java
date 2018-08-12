package com.mihailenko.ilya.weatherforecastapp.ui.presenter.searchweather;

import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.business.currentweather.ICurrentWeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.business.searchweather.SearchWeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.interfaces.MessageShower;
import com.mihailenko.ilya.weatherforecastapp.ui.presenter.base.BasePresenter;
import com.mihailenko.ilya.weatherforecastapp.ui.view.searchweather.SearchWeatherView;
import com.mihailenko.ilya.weatherforecastapp.utils.rx.RxSchedulers;

import java.util.Collections;

/**
 * Created by Ilya on 12.06.2017.
 */

public class SearchWeatherPresenter extends BasePresenter<SearchWeatherView> {

    private final ICurrentWeatherInteractor currentWeatherInteractor;
    private final SearchWeatherInteractor searchWeatherInteractor;

    public SearchWeatherPresenter(SearchWeatherView view,
                                  ICurrentWeatherInteractor currentWeatherInteractor,
                                  SearchWeatherInteractor searchWeatherInteractor) {
        super(view);

        this.currentWeatherInteractor = currentWeatherInteractor;
        this.searchWeatherInteractor = searchWeatherInteractor;
    }

    public void onCitySelected(String city) {
        addDisposable(currentWeatherInteractor.getForecast(city)
                .compose(RxSchedulers.applySingleAsync())
                .doOnSubscribe(any -> view.onStartProgress())
                .doFinally(view::onEndProgress)
                .subscribe(view::showForecast, throwable -> {
                    view.showMessage(throwable);
                    view.setToolbarTittle(R.string.find_city);
                    view.showForecast(Collections.emptyList());
                }));
    }

    public void onSearchCitySuggestions(String input) {
        addDisposable(searchWeatherInteractor.findSuggestions(input)
                .compose(RxSchedulers.applySingleAsync())
                .subscribe(view::showCitySuggestions, view::showMessage)
        );
    }
}
