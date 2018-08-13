package com.mihailenko.ilya.weatherforecastapp.ui.presentation.view.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.ui.presentation.presenter.base.BasePresenter;


/**
 * Created by ru.smedialink on 25.01.17.
 */
public abstract class ToolbarActivity<TBinding extends ViewDataBinding, TPresenter extends BasePresenter>
        extends BaseActivity<TBinding, TPresenter> {
    protected Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
    }

    protected abstract int getToolbarTitle();

    private void setupActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(getToolbarTitle());
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back_arrow);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setToolbarTittle(int tittle) {
        toolbar.setTitle(tittle);
    }

    public void setToolbarTittle(String tittle) {
        toolbar.setTitle(tittle);
    }
}
