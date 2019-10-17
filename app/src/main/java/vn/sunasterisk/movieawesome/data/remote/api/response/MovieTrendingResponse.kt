package vn.sunasterisk.movieawesome.data.remote.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import vn.sunasterisk.movieawesome.data.entity.MovieTrending

@Parcelize
data class MovieTrendingResponse (
    @SerializedName("results")
    val moviesTrending: List<MovieTrending>
) : Parcelable
