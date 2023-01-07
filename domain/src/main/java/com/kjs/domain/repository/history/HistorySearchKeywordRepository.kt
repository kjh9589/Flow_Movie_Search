package com.kjs.domain.repository.history

import com.kjs.domain.usecase.history.InsertHistoryKeywordUseCase
import com.kjs.model.history.HistorySearchKeywordModel


interface HistorySearchKeywordRepository {
    suspend fun insertKeyword(params: InsertHistoryKeywordUseCase.Params)
    suspend fun getHistoryKeywordList(): List<HistorySearchKeywordModel>
}