package com.kjs.data.db.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tableHistorySearchKeyword")
data class HistorySearchKeywordEntity(
    @PrimaryKey
    @ColumnInfo(name = "keyword")
    val keyword: String,
    @ColumnInfo(name = "created_time")
    val createdTime: Long
)