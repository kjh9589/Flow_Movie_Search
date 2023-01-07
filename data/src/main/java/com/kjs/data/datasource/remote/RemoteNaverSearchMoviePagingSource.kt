package com.kjs.data.datasource.remote

import android.content.Context
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kjs.data.R
import com.kjs.data.api.NaverSearchMovieService
import com.kjs.data.response.movie.toMovieModel
import com.kjs.data.util.convertCommonApiResult
import com.kjs.domain.di.IoDispatcher
import com.kjs.domain.result.CommonResult
import com.kjs.domain.result.CommonResultState
import com.kjs.domain.usecase.movie.SearchNaverMovieUseCase
import com.kjs.model.movie.MovieModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.http.Query
import javax.inject.Inject

class RemoteNaverSearchMoviePagingSource @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val api: NaverSearchMovieService,
    private val keyword: String,
    private val context: Context
) : PagingSource<Int, MovieModel>() {
    companion object {
        const val START_IDX = 1
        const val DISPLAY_COUNT = 30
    }

    private var idx = START_IDX

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            withContext(dispatcher) {
                val result = api.getMovieList(
                    query = keyword,
                    start = idx,
                    display = DISPLAY_COUNT
                )

                val data = result.convertCommonApiResult()

                if (data.status == CommonResultState.FAILURE) {
                    val exception = Exception("${data.message}")
                    throw exception
                }
                
                if (data.data!!.total == 0) {
                    val exception = Exception(context.getString(R.string.error_empty_search_result))
                    throw exception
                }

                val prevKey = null
                val nextKey = if (data.data!!.total < idx + DISPLAY_COUNT) null else idx + DISPLAY_COUNT

                idx += DISPLAY_COUNT

                LoadResult.Page(data = data.data!!.items.map { it.toMovieModel() }, prevKey, nextKey)

            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}