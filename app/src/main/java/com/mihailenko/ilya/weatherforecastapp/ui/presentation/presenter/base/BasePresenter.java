package com.mihailenko.ilya.weatherforecastapp.ui.presentation.presenter.base;

import android.support.annotation.CallSuper;

import com.mihailenko.ilya.weatherforecastapp.ui.presentation.view.base.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ilya on 11.06.2017.
 */

public abstract class BasePresenter<TView extends BaseView> {
    protected TView view;
    private CompositeDisposable compositeSubscription;


    public BasePresenter(TView view) {
        this.view = view;
    }

    @SuppressWarnings("EmptyMethod")
    @CallSuper
    public void onCreate() {
    }

    @CallSuper
    public void onDestroy() {
        clearDisposables();
    }

    @SuppressWarnings("EmptyMethod")
    @CallSuper
    public void onStart() {
    }

    @SuppressWarnings("EmptyMethod")
    public void onResume() {
    }

    @SuppressWarnings("EmptyMethod")
    @CallSuper
    public void onStop() {
        clearDisposables();
    }


    final protected void addDisposable(Disposable disposable) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeDisposable();
        }
        compositeSubscription.add(disposable);
    }

    private void clearDisposables() {
        if (compositeSubscription != null) {
            compositeSubscription.clear();
        }
    }
}
