package vn.sunasterisk.movieawesome.data.remote.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import vn.sunasterisk.movieawesome.data.entity.Genre

@Parcelize
data class GenresResponse(
    @SerializedName("results")
    val genres: List<Genre>
) : Parcelable
