package com.kjs.data.datasource.local.history.di

import com.kjs.data.datasource.local.history.impl.LocalHistorySearchKeywordDataSourceImpl
import com.kjs.data.datasource.local.history.interfaces.LocalHistorySearchKeywordDataSource
import com.kjs.data.db.history.HistorySearchKeywordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class LocalHistoryDataSourceModule {
    @Provides
    fun provideHistorySearchKeywordDataSource(
        dao: HistorySearchKeywordDao
    ): LocalHistorySearchKeywordDataSource {
        return LocalHistorySearchKeywordDataSourceImpl(dao)
    }
}