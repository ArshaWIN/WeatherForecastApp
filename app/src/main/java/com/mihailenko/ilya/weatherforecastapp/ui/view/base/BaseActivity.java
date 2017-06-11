package com.mihailenko.ilya.weatherforecastapp.ui.view.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import com.mihailenko.ilya.weatherforecastapp.WeatherApplication;
import com.mihailenko.ilya.weatherforecastapp.di.ActivityComponent;
import com.mihailenko.ilya.weatherforecastapp.di.ActivityModule;
import com.mihailenko.ilya.weatherforecastapp.di.DaggerActivityComponent;
import com.mihailenko.ilya.weatherforecastapp.di.HasActivityComponent;
import com.mihailenko.ilya.weatherforecastapp.di.app.AppComponent;
import com.mihailenko.ilya.weatherforecastapp.interfaces.CanShowMessage;
import com.mihailenko.ilya.weatherforecastapp.interfaces.MessageShower;
import com.mihailenko.ilya.weatherforecastapp.ui.presenter.base.BasePresenter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Ilya on 11.06.2017.
 */

public abstract class BaseActivity<TBinding extends ViewDataBinding, TPresenter extends BasePresenter> extends AppCompatActivity
        implements HasActivityComponent, CanShowMessage {

    protected TBinding binding;
    private ActivityComponent activityComponent;
    private CompositeSubscription compositeSubscription;

    @Inject
    MessageShower messageShower;


    @Override
    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        buildBaseActivityComponent();
        buildActivityComponentAndInject();

        super.onCreate(savedInstanceState);

        binding = inflateBinding();

        if (getPresenter() != null) {
            getPresenter().onCreate();
        }

    }


    private void buildBaseActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }


    public abstract void buildActivityComponentAndInject();

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

    protected abstract TBinding inflateBinding();


    @Override
    protected void onStop() {
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
        unsubscribeComposite();

        super.onStop();
    }

    @Override
    protected void onDestroy() {

        if (getPresenter() != null) {
            getPresenter().onDestroy();
        }
        super.onDestroy();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getPresenter() != null) {
            getPresenter().onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getPresenter() != null) {
            getPresenter().onResume();
        }
    }

    @Nullable
    public TBinding getBinding() {
        return binding;
    }

    protected AppComponent getAppComponent() {
        return WeatherApplication.getApplication().getAppComponent();
    }

    @Nullable
    public abstract TPresenter getPresenter();

    @Override
    public void showMessage(String message) {
        messageShower.showMessage(message);
    }

    @Override
    public void showMessage(@StringRes int messageRes) {
        messageShower.showMessage(messageRes);
    }

    @Override
    public void showMessage(Throwable throwable) {
        messageShower.showMessage(throwable);
    }
}
