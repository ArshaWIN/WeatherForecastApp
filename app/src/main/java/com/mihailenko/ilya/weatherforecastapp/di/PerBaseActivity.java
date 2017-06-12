package com.mihailenko.ilya.weatherforecastapp.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Ilya on 12.06.2017.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerBaseActivity {
}
