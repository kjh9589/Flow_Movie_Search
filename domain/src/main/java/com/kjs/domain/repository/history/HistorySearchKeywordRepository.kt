package com.kjs.domain.repository.history

import com.kjs.domain.usecase.history.InsertHistoryKeywordUseCase
import com.kjs.model.history.HistorySearchKeyword


interface HistorySearchKeywordRepository {
    suspend fun insertKeyword(params: InsertHistoryKeywordUseCase.Params)
    suspend fun getHistoryKeywordList(): List<HistorySearchKeyword>
}