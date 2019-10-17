package vn.sunasterisk.movieawesome.data.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import vn.sunasterisk.movieawesome.data.entity.Movie

@Parcelize
data class MoviesResponse(
    @SerializedName("results")
    val movies: List<Movie>
) : Parcelable
