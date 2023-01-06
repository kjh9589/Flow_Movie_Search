package com.kjs.data.datasource.local.history.interfaces

import com.kjs.model.history.HistorySearchKeyword

interface LocalHistorySearchKeywordDataSource {
    suspend fun insert(keyword: String)
    suspend fun getHistoryKeywordList(): List<HistorySearchKeyword>
}