package com.kjs.data.db.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistorySearchKeywordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(keyword: HistorySearchKeywordEntity)

    @Query("SELECT * FROM tableHistorySearchKeyword ORDER BY created_time DESC LIMIT 10")
    suspend fun getSearchKeywordList(): List<HistorySearchKeywordEntity>
}