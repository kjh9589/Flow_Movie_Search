package com.kjs.data.repository_impl.local.history

import com.kjs.data.datasource.local.history.interfaces.LocalHistorySearchKeywordDataSource
import com.kjs.domain.repository.history.HistorySearchKeywordRepository
import com.kjs.domain.usecase.history.InsertHistoryKeywordUseCase
import com.kjs.model.history.HistorySearchKeywordModel
import javax.inject.Inject

class HistorySearchKeywordRepositoryImpl @Inject constructor(
    private val dataSource: LocalHistorySearchKeywordDataSource
): HistorySearchKeywordRepository {
    override suspend fun insertKeyword(params: InsertHistoryKeywordUseCase.Params) {
       dataSource.insert(params.keyword)
    }

    override suspend fun getHistoryKeywordList(): List<HistorySearchKeywordModel> {
        return dataSource.getHistoryKeywordList()
    }
}