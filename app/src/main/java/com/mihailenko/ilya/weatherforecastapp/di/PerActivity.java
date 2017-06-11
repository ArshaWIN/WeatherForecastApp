package ru.smedialink.eagleviewer.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by ilyamikhailenko on 02/02/2017.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerBaseActivity {
}