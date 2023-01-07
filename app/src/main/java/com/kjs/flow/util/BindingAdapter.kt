package com.kjs.flow.util

import android.text.Html
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.kjs.flow.R

@BindingAdapter("loadImage")
fun ImageFilterView.loadImage(uri: String?) {
    Glide.with(this)
        .load(uri)
        .placeholder(R.drawable.ic_baseline_image_not_supported_24)
        .into(this)
}

@BindingAdapter("titleWithHtml")
fun TextView.titleWithHtml(html: String) {
    this.text = "${this.context.getString(R.string.general_title)} : ${Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)}"
}

@BindingAdapter("publishDate")
fun TextView.publishDate(publishDate: String) {
    this.text = "${this.context.getString(R.string.general_publish_date)} : $publishDate"
}

@BindingAdapter("rating")
fun TextView.rating(rating: String) {
    this.text = "${this.context.getString(R.string.general_rating)} : $rating"
}