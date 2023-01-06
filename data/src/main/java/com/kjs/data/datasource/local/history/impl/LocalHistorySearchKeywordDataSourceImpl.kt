package com.kjs.data.datasource.local.history.impl

import com.kjs.data.datasource.local.history.interfaces.LocalHistorySearchKeywordDataSource
import com.kjs.data.db.history.HistorySearchKeywordDao
import com.kjs.data.db.history.HistorySearchKeywordEntity
import com.kjs.model.history.HistorySearchKeyword
import java.util.*
import javax.inject.Inject

class LocalHistorySearchKeywordDataSourceImpl @Inject constructor(
    private val dao: HistorySearchKeywordDao
): LocalHistorySearchKeywordDataSource{
    override suspend fun insert(keyword: String) {
        dao.insert(HistorySearchKeywordEntity(keyword, Date().time))
    }

    override suspend fun getHistoryKeywordList(): List<HistorySearchKeyword> {
        return dao.getSearchKeywordList().map { HistorySearchKeyword(it.keyword) }
    }
}