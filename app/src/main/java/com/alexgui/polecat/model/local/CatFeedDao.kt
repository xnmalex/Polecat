package com.alexgui.polecat.model.local

import androidx.room.*
import com.alexgui.polecat.model.data.CatFeed

@Dao
interface CatFeedDao {
    @Query("SELECT * FROM catfeed")
    suspend fun getCatFeed(): List<CatFeed>

    @Delete
    suspend fun delete(catFeed: CatFeed)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(catFeed: CatFeed)
}