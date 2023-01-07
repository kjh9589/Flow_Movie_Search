package com.kjs.data.datasource.local.history.interfaces

import com.kjs.model.history.HistorySearchKeywordModel

interface LocalHistorySearchKeywordDataSource {
    suspend fun insert(keyword: String)
    suspend fun getHistoryKeywordList(): List<HistorySearchKeywordModel>
}