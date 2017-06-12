package com.mihailenko.ilya.weatherforecastapp.ui.presenter.searchweather;

import com.mihailenko.ilya.weatherforecastapp.business.currentweather.ICurrentWeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.business.searchweather.ISearchWeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.ui.view.searchweather.SearchWeatherView;
import com.mihailenko.ilya.weatherforecastapp.utils.rx.RxSchedulers;

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
                .subscribe(view::showForecast, view::showMessage));
    }
}
