package com.kjs.data.repository_impl.remote.movie

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kjs.data.api.NaverSearchMovieService
import com.kjs.data.datasource.remote.RemoteNaverSearchMoviePagingSource
import com.kjs.domain.di.IoDispatcher
import com.kjs.domain.repository.movie.NaverMovieSearchRepository
import com.kjs.model.movie.MovieModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NaverMovieSearchRepositoryImpl @Inject constructor(
    private val api: NaverSearchMovieService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val context: Context
): NaverMovieSearchRepository {
    override suspend fun searchNaverMovieList(keyword: String): Flow<PagingData<MovieModel>> {
        return Pager(PagingConfig(RemoteNaverSearchMoviePagingSource.DISPLAY_COUNT)) {
            RemoteNaverSearchMoviePagingSource(
                dispatcher = dispatcher,
                api = api,
                keyword = keyword,
                context = context
            )
        }.flow
    }
}