package com.kjs.data.api

import com.kjs.data.response.movie.NaverSearchMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverSearchMovieService {
    @GET("v1/search/movie.json")
    suspend fun getMovieList(
        @Query("query") query: String,
        @Query("start") start: Int,
        @Query("display") display: Int
    ): Response<NaverSearchMovieResponse>
}