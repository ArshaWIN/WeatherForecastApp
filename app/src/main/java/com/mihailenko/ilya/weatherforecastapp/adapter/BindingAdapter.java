package com.mihailenko.ilya.weatherforecastapp.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.utils.StringUtils;

import java.util.Locale;

/**
 * Created by Ilya on 12.06.2017.
 */

public class BindingAdapter {
    @android.databinding.BindingAdapter("imageUrl")
    public static void loadImage(final ImageView imageView, final String icon) {
        if (StringUtils.isEmpty(icon)) return;

        Glide.with(imageView.getContext())
                .load(icon)
                .placeholder(R.drawable.ic_umbrella)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }
}
