package com.mihailenko.ilya.weatherforecastapp.ui.presenter.searchweather;

import com.mihailenko.ilya.weatherforecastapp.ui.presenter.base.BasePresenter;
import com.mihailenko.ilya.weatherforecastapp.ui.view.searchweather.SearchWeatherView;

/**
 * Created by Ilya on 12.06.2017.
 */

public abstract class SearchWeatherPresenter extends BasePresenter<SearchWeatherView> {

    public SearchWeatherPresenter(SearchWeatherView view) {
        super(view);
    }


    public abstract void onCitySelected(String city);
}
