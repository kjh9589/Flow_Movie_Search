package com.kjs.data.response.movie


import com.google.gson.annotations.SerializedName

data class NaverSearchMovieResponse(
    @SerializedName("display")
    val display: Int = 0,
    @SerializedName("items")
    val items: List<NaverSearchMovieItem> = listOf(),
    @SerializedName("lastBuildDate")
    val lastBuildDate: String = "",
    @SerializedName("start")
    val start: Int = 0,
    @SerializedName("total")
    val total: Int = 0
)