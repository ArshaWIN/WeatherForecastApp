package com.mihailenko.ilya.weatherforecastapp.network.weather;

import com.google.gson.Gson;
import com.mihailenko.ilya.weatherforecastapp.network.interceptors.NetworkErrorInterceptor;
import com.mihailenko.ilya.weatherforecastapp.network.interceptors.QueryInterceptor;
import com.mihailenko.ilya.weatherforecastapp.network.places.GooglePlacesApi;
import com.mihailenko.ilya.weatherforecastapp.network.weather.WeatherApi;
import com.mihailenko.ilya.weatherforecastapp.network.weather.WeatherApiConstans;
import com.mihailenko.ilya.weatherforecastapp.network.weather.WeatherApiProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by Ilya on 11.06.2017.
 */
@Singleton
public class WeatherNetworkService implements WeatherApiProvider {
    private static final String BASE_URL = WeatherApiConstans.BASE_URL;

    private WeatherApi weatherApi;

    private static final long NETWORK_TIMEOUT_SECONDS = 30;

    @Inject
    public WeatherNetworkService(HttpLoggingInterceptor loggingInterceptor,
                                 NetworkErrorInterceptor networkErrorInterceptor,
                                 Gson gson) {


        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(networkErrorInterceptor)
                .connectTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(getUrlWithKey())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()));


        weatherApi = retrofitBuilder
                .client(okHttpClientBuilder.build())
                .build()
                .create(WeatherApi.class);

    }

    private String getUrlWithKey() {
        return BASE_URL + WeatherApiConstans.WEATHER_API_KEY + "/";
    }


    @Override
    public WeatherApi getWeatherApi() {
        return weatherApi;
    }

}
