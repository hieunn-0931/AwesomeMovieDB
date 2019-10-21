package vn.sunasterisk.movieawesome.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Movie")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null
) : Parcelable
