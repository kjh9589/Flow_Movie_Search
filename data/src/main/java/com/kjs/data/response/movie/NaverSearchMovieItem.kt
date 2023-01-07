package com.kjs.data.response.movie


import com.google.gson.annotations.SerializedName
import com.kjs.model.movie.MovieModel

data class NaverSearchMovieItem(
    @SerializedName("actor")
    val actor: String = "",
    @SerializedName("director")
    val director: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("link")
    val link: String = "",
    @SerializedName("pubDate")
    val pubDate: String = "",
    @SerializedName("subtitle")
    val subtitle: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("userRating")
    val userRating: String = ""
)

fun NaverSearchMovieItem.toMovieModel(): MovieModel {
    return MovieModel(
        title = title,
        publishDate = pubDate,
        imageUri = image,
        rating = userRating,
        webLink = link
    )
}