package com.kjs.data.repository_impl

import com.kjs.data.datasource.local.history.interfaces.LocalHistorySearchKeywordDataSource
import com.kjs.data.db.history.HistorySearchKeywordDao
import com.kjs.data.repository_impl.local.history.HistorySearchKeywordRepositoryImpl
import com.kjs.domain.repository.history.HistorySearchKeywordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object HistorySearchKeywordRepositoryModule {
    @Provides
    fun provideHistorySearchKeywordRepository(
        dataSource: LocalHistorySearchKeywordDataSource
    ): HistorySearchKeywordRepository {
        return HistorySearchKeywordRepositoryImpl(dataSource)
    }
}