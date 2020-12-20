package com.example.watcher.data.local

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromGenreIds(genreIds: List<Int>): String {
        return genreIds.joinToString(",")
    }

    @TypeConverter
    fun toGenreIds(genreIds: String): List<Int> {
        val genreStringsArray = genreIds.split(",")
        return genreStringsArray.map { it.toInt() }
    }
}
