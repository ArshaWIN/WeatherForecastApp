package com.mihailenko.ilya.weatherforecastapp.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mihailenko.ilya.weatherforecastapp.BR;
import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.data.item.ForecastDayItem;
import com.mihailenko.ilya.weatherforecastapp.databinding.ItemCurrentWeatherBinding;
import com.mihailenko.ilya.weatherforecastapp.databinding.ItemDayForecastBinding;

import java.util.List;

/**
 * Created by Ilya on 12.06.2017.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;


    private List<ForecastDayItem> items;
    private LayoutInflater layoutInflater;

    public ForecastAdapter(List<ForecastDayItem> items) {
        this.items = items;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        if (viewType == TYPE_HEADER) {
            ItemCurrentWeatherBinding binding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.item_current_weather, parent, false);
            return new ForecastCurrentWeather(binding.getRoot());
        }
        ItemDayForecastBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_day_forecast, parent, false);
        return new ForecastDay(binding.getRoot());

    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEADER : TYPE_NORMAL;
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        if (items == null) {
            return;
        }
        holder.setItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void setForecast(List<ForecastDayItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    abstract class ForecastViewHolder<TBinding extends ViewDataBinding> extends RecyclerView.ViewHolder {
        private final TBinding binding;

        ForecastViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }

        void setItem(ForecastDayItem item) {
            binding.setVariable(BR.forecast, item);
        }
    }

    private class ForecastCurrentWeather extends ForecastViewHolder<ItemCurrentWeatherBinding> {
        ForecastCurrentWeather(View view) {
            super(view);
        }
    }

    private class ForecastDay extends ForecastViewHolder<ItemDayForecastBinding> {
        ForecastDay(View view) {
            super(view);
        }
    }
}
