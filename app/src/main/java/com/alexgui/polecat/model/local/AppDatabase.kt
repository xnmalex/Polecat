package com.alexgui.polecat.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alexgui.polecat.model.data.CatFeed

@Database(entities = [CatFeed::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun catFeedDao(): CatFeedDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
    }

}