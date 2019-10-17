package vn.sunasterisk.movieawesome.data.remote.api

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import vn.sunasterisk.movieawesome.data.remote.api.response.GenresResponse
import vn.sunasterisk.movieawesome.data.remote.api.response.MovieTrendingResponse
import vn.sunasterisk.movieawesome.data.remote.api.response.MoviesResponse

interface ApiService {
    @GET("3/genre/movie/list")
    suspend fun getListGenreAsync(): Deferred<GenresResponse>

    @GET("3/movie/{type}")
    suspend fun getListMoviesByCategory(
        @Path("type") type: String,
        @Query("page") page: Int
    ): MoviesResponse

    @GET("3/trending/movie/day")
    suspend fun getMoviesTrendingByDay(): MovieTrendingResponse
}
