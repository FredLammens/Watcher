package com.example.watcher.models.movies


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents a movie object
 * @property adult true if its an adult film
 * @property backdropPath background image path
 * @property genreIds id of genreObject
 * @property id  id of movieObject
 * @property originalLanguage the original language of the movie
 * @property overview description of movie
 * @property popularity score based on number of votes, views for the day , how many marked it as favourite etc.
 * @property posterPath relative path of poster image
 * @property releaseDate the movie's release date
 * @property title the title of the movie
 * @property video if the movie has a trailer is true
 * @property voteAverage score from 1 to 5 base on votes
 * @property voteCount count of how many people voted
 */
@Entity(tableName = "movies")
@JsonClass(generateAdapter = true)
data class Result(
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @Json(name = "poster_path")
    var posterPath: String,
    @Json(name = "release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int
)