package com.estebanserrano.ktestfarmatodo.presentation.extension

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.getImageByUrl(url: String) {
    Picasso.with(context).load(url).fit().into(this)
}

fun ImageView.getImageDetailByUrl(url: String) {
    Picasso.with(context).load(url).into(this)
}