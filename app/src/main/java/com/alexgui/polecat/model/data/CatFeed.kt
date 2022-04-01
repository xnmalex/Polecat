package com.alexgui.polecat.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catfeed")
class CatFeed(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int,

    @ColumnInfo(name = "image")
    val image: String?,

    @ColumnInfo(name = "fact")
    val fact: String?
){

}
