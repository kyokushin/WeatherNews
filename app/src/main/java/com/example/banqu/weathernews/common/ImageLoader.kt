package com.example.banqu.weathernews.common

import android.widget.ImageView
import com.example.banqu.weathernews.R
import com.squareup.picasso.Picasso

/**
 * Created by banqu on 2018/03/21.
 */
class ImageLoader {

    fun loadImage(imageView: ImageView, url: String) {
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_refresh_black_24dp)
                .error(R.drawable.ic_error_outline_black_24dp)
                .into(imageView)
    }

}