package com.kjs.data.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object NaverApiModule {
    @Provides
    fun provideNaverSearchMovieService(
        retrofit: Retrofit
    ): NaverSearchMovieService {
        return retrofit.create(NaverSearchMovieService::class.java)
    }
}