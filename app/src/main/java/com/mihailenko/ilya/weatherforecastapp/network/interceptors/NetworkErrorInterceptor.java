package com.mihailenko.ilya.weatherforecastapp.network.interceptors;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.mihailenko.ilya.weatherforecastapp.errors.NoInternetConnectionThrowable;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class NetworkErrorInterceptor implements Interceptor {
    private final ConnectivityManager mConnectivityManager;

    public NetworkErrorInterceptor(ConnectivityManager connectivityManager) {
        mConnectivityManager = connectivityManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!isNetworkEnabled()) {
            throw new NoInternetConnectionThrowable();
        }
        return chain.proceed(chain.request());
    }

    private boolean isNetworkEnabled() {
        final NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}