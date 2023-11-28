package com.example.mealapp.ui.themes

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["imageUrl"])
fun setImage(view: ImageView, url: String?) {
    Glide.with(view)
        .load(url)
        .into(view)
}