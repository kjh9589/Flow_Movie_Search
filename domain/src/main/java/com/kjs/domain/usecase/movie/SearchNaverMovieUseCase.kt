package com.kjs.domain.usecase.movie

import androidx.paging.PagingData
import com.kjs.domain.di.IoDispatcher
import com.kjs.domain.repository.movie.NaverMovieSearchRepository
import com.kjs.domain.usecase.SuspendUseCase
import com.kjs.model.movie.MovieModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNaverMovieUseCase @Inject constructor(
    private val repository: NaverMovieSearchRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): SuspendUseCase<String, Flow<PagingData<MovieModel>>>(dispatcher) {
    override suspend fun execute(parameters: String): Flow<PagingData<MovieModel>> {
        return repository.searchNaverMovieList(parameters)
    }
}