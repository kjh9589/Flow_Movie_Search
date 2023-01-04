package com.kjs.domain.repository

import androidx.paging.PagingData
import com.kjs.domain.usecase.movie.SearchNaverMovieUseCase
import com.kjs.model.movie.MovieModel
import kotlinx.coroutines.flow.Flow

interface NaverMovieSearchRepository {
    suspend fun searchNaverMovieList(
        keyword: String
    ): Flow<PagingData<MovieModel>>
}