package com.alexgui.polecat.model.local

import androidx.room.TypeConverter
import com.alexgui.polecat.model.data.CatFeed
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromWeatherList(weather: List<CatFeed?>?): String? {
        val type = object : TypeToken<List<CatFeed>>() {}.type
        return Gson().toJson(weather, type)
    }

    @TypeConverter
    fun toWeatherList(weatherString: String?): List<CatFeed>? {
        val type = object : TypeToken<List<CatFeed>>() {}.type
        return Gson().fromJson<List<CatFeed>>(weatherString, type)
    }
}