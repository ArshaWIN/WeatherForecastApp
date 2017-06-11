package com.mihailenko.ilya.weatherforecastapp.ui.presenter.base;

import android.support.annotation.CallSuper;

import com.mihailenko.ilya.weatherforecastapp.ui.view.base.BaseView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Ilya on 11.06.2017.
 */

public abstract class BasePresenter<TView extends BaseView> {
    protected TView view;
    private CompositeSubscription compositeSubscription;


    public BasePresenter(TView view) {
        this.view = view;
    }

    @SuppressWarnings("EmptyMethod")
    @CallSuper
    public void onCreate() {
    }

    @CallSuper
    public void onDestroy() {
        unsubscribeComposite();
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
        unsubscribeComposite();
    }


    final protected void addSubscription(Subscription subscription) {
        if (compositeSubscription == null || compositeSubscription.isUnsubscribed()) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(subscription);
    }

    private void unsubscribeComposite() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
            compositeSubscription = null;
        }
    }


}
