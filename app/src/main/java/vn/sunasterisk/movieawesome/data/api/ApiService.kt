package vn.sunasterisk.movieawesome.data.api

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import vn.sunasterisk.movieawesome.data.api.response.GenresResponse
import vn.sunasterisk.movieawesome.data.api.response.MoviesResponse

interface ApiService {
    @GET("3/genre/movie/list")
    suspend fun getListGenre(): GenresResponse

    @GET("3/movie/{type}")
    suspend fun getListMoviesByCategory(
        @Path("type") type: String,
        @Query("page") page: Int
    ): MoviesResponse
}
