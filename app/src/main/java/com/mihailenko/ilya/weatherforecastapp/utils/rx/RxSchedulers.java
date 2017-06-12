package com.mihailenko.ilya.weatherforecastapp.utils.rx;

import rx.Observable;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RxSchedulers {

    public static <T> Observable.Transformer<T, T> getIOToMainTransformer() {
        return objectObservable -> objectObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
