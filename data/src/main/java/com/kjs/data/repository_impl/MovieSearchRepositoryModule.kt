package com.kjs.data.repository_impl

import com.kjs.data.api.NaverSearchMovieService
import com.kjs.data.datasource.remote.RemoteNaverSearchMoviePagingSource
import com.kjs.data.repository_impl.remote.movie.NaverMovieSearchRepositoryImpl
import com.kjs.domain.di.IoDispatcher
import com.kjs.domain.repository.movie.NaverMovieSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@InstallIn(SingletonComponent::class)
@Module
class MovieSearchRepositoryModule {
    @Provides
    fun provideNaverMovieSearchRepository(
        api: NaverSearchMovieService,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): NaverMovieSearchRepository {
        return NaverMovieSearchRepositoryImpl(api, dispatcher)
    }
}