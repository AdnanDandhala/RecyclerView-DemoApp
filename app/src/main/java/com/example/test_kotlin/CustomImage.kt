package com.example.test_kotlin

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["imageById"])
fun ImageView.setImageId(id: Int) {
    this.setImageResource(id)
}

@BindingAdapter(value = ["imageUrl"])
fun setImageUrl(imageView: ImageView, id: Int) {
    imageView.setImageResource(id)
}
// Ok I Have To Pass Both Image View And Id