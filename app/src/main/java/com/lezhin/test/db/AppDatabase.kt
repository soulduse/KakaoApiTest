package com.lezhin.test.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.lezhin.test.db.search.Search
import com.lezhin.test.db.search.SearchDao

@Database(entities = [Search::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun searchDao(): SearchDao

    companion object {

        private const val DB_NAME = "SearchHistory"

        @Volatile private var instance: AppDatabase?= null

        fun getDatabase(context: Context): AppDatabase =
                instance ?: synchronized(this) {
                    instance ?: buildDatabase(context).also { instance = it }
                }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, DB_NAME)
                    .build()
    }
}
