package com.mihailenko.ilya.weatherforecastapp.ui.presenter.searchweather;

import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.business.currentweather.ICurrentWeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.business.searchweather.ISearchWeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.ui.view.searchweather.SearchWeatherView;
import com.mihailenko.ilya.weatherforecastapp.utils.rx.RxSchedulers;

import java.util.Collections;

/**
 * Created by Ilya on 12.06.2017.
 */

public class SearchWeatherPresenterImpl extends SearchWeatherPresenter {

    private final ICurrentWeatherInteractor currentWeatherInteractor;

    public SearchWeatherPresenterImpl(SearchWeatherView view,
                                      ICurrentWeatherInteractor currentWeatherInteractor) {
        super(view);

        this.currentWeatherInteractor = currentWeatherInteractor;
    }

    @Override
    public void onCitySelected(String city) {
        addSubscription(currentWeatherInteractor.getForecast(city)
                .compose(RxSchedulers.getIOToMainTransformer())
                .doOnSubscribe(view::onStartProgress)
                .doAfterTerminate(view::onEndProgress)
                .subscribe(view::showForecast, throwable -> {
                    view.showMessage(throwable);
                    view.setToolbarTittle(R.string.find_city);
                    view.showForecast(Collections.emptyList());
                }));
    }
}
