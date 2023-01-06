package com.kjs.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kjs.data.db.history.HistorySearchKeywordDao
import com.kjs.data.db.history.HistorySearchKeywordEntity

@Database(
    entities = [HistorySearchKeywordEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun historySearchKeywordDao(): HistorySearchKeywordDao

    companion object {
        const val databaseName = "FlowDb"
    }
}