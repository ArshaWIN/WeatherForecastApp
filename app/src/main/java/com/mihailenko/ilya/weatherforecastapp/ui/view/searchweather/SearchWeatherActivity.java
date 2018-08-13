package com.mihailenko.ilya.weatherforecastapp.ui.view.searchweather;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.adapter.CitiesAdapter;
import com.mihailenko.ilya.weatherforecastapp.adapter.ForecastAdapter;
import com.mihailenko.ilya.weatherforecastapp.data.item.ForecastDayItem;
import com.mihailenko.ilya.weatherforecastapp.databinding.ActivitySearchWeatherBinding;
import com.mihailenko.ilya.weatherforecastapp.di.searchweather.DaggerSearchWeatherComponent;
import com.mihailenko.ilya.weatherforecastapp.di.searchweather.SearchWeatherModule;
import com.mihailenko.ilya.weatherforecastapp.ui.presenter.searchweather.SearchWeatherPresenter;
import com.mihailenko.ilya.weatherforecastapp.ui.view.base.ToolbarActivity;
import com.mihailenko.ilya.weatherforecastapp.widget.ItemDivider;
import com.mihailenko.ilya.weatherforecastapp.widget.LoadingIndicator;

import java.util.List;

import javax.inject.Inject;

import static com.mihailenko.ilya.weatherforecastapp.utils.StringUtils.EMPTY_STRING;

/**
 * Created by Ilya on 12.06.2017.
 */

public class SearchWeatherActivity extends ToolbarActivity<ActivitySearchWeatherBinding, SearchWeatherPresenter>
        implements SearchWeatherView, OnCitySearchListener {

    @Inject
    SearchWeatherPresenter presenter;
    @Inject
    CitiesAdapter citiesAdapter;
    @Inject
    LoadingIndicator loadingIndicator;
    @Inject
    ForecastAdapter forecastAdapter;
    @Inject
    RecyclerView.LayoutManager layoutManager;
    @Inject
    ItemDivider itemDivider;

    public static void start(Context context) {
        Intent starter = new Intent(context, SearchWeatherActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.find_city;
    }

    @Override
    public void buildActivityComponentAndInject() {
        DaggerSearchWeatherComponent.builder()
                .activityComponent(getActivityComponent())
                .searchWeatherModule(new SearchWeatherModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.setHasData(false);
        initAdapter();
        initAutoComplete();
    }

    private void initAutoComplete() {
        binding.autocompleteCities.setAdapter(citiesAdapter);
        binding.autocompleteCities.setThreshold(1);
        binding.autocompleteCities.setOnItemClickListener((parent, view, position, id) -> onCitySelected(citiesAdapter.getItem(position)));
    }

    private void initAdapter() {
        binding.weatherList.setLayoutManager(layoutManager);
        binding.weatherList.addItemDecoration(itemDivider);
        binding.weatherList.setAdapter(forecastAdapter);
    }

    private void onCitySelected(String city) {
        presenter.onCitySelected(city);
        setToolbarTittle(city);
        binding.autocompleteCities.setText(EMPTY_STRING);
    }

    @Override
    protected ActivitySearchWeatherBinding inflateBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_search_weather);
    }

    @Nullable
    @Override
    public SearchWeatherPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void startProgress() {
        loadingIndicator.show();
    }

    @Override
    public void finishProgress() {
        loadingIndicator.dismiss();
    }

    @Override
    public void showForecast(List<ForecastDayItem> forecastDayItems) {
        forecastAdapter.setForecast(forecastDayItems);
        binding.setHasData(!forecastDayItems.isEmpty());
        hideKeyboard();
    }

    @Override
    public void showCitySuggestions(List<String> cities) {
        citiesAdapter.updateCities(cities);
    }

    @Override
    public void setToolbarTittle(String tittle) {
        super.setToolbarTittle(tittle);
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onCitySearch(String input) {
        presenter.onSearchCitySuggestions(input);
    }
}
