package com.mihailenko.ilya.weatherforecastapp.network.places;

import com.google.gson.Gson;
import com.mihailenko.ilya.weatherforecastapp.network.interceptors.NetworkErrorInterceptor;
import com.mihailenko.ilya.weatherforecastapp.network.interceptors.QueryInterceptor;
import com.mihailenko.ilya.weatherforecastapp.network.weather.WeatherApi;
import com.mihailenko.ilya.weatherforecastapp.network.weather.WeatherApiConstans;
import com.mihailenko.ilya.weatherforecastapp.network.weather.WeatherApiProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by Ilya on 12.06.2017.
 */

public class GooglePlacesNetworkService implements GooglePlacesApiProvider {
    private static final String BASE_URL = GooglePlacesApiConstans.GOOGLE_API_BASE_URL;

    private GooglePlacesApi placesApi;

    private static final long NETWORK_TIMEOUT_SECONDS = 30;

    @Inject
    public GooglePlacesNetworkService(HttpLoggingInterceptor loggingInterceptor,
                                      NetworkErrorInterceptor networkErrorInterceptor,
                                      QueryInterceptor queryInterceptor,
                                      Gson gson) {


        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(networkErrorInterceptor)
                .addInterceptor(queryInterceptor)
                .connectTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()));


        placesApi = retrofitBuilder
                .client(okHttpClientBuilder.build())
                .build()
                .create(GooglePlacesApi.class);

    }


    @Override
    public GooglePlacesApi getPlacesApi() {
        return placesApi;
    }
}
