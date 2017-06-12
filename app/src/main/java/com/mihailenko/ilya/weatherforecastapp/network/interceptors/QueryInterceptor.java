package com.mihailenko.ilya.weatherforecastapp.network.interceptors;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.schedulers.Schedulers;

public class QueryInterceptor implements Interceptor {
    private final Map<String, String> queries;

    public QueryInterceptor(Map<String, String> queries) {
        this.queries = queries;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        final HttpUrl.Builder builder = request.url().newBuilder();

        Observable.from(queries.entrySet())
                .subscribeOn(Schedulers.immediate())
                .subscribe(entry -> builder.addQueryParameter(entry.getKey(), entry.getValue()));

        request = request.newBuilder().url(builder.build()).build();

        return chain.proceed(request);
    }

}