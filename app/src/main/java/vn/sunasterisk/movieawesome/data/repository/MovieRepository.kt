package vn.sunasterisk.movieawesome.data.repository

import vn.sunasterisk.movieawesome.data.entity.Genre
import vn.sunasterisk.movieawesome.data.entity.Movie
import vn.sunasterisk.movieawesome.data.remote.api.response.MovieTrendingResponse
import vn.sunasterisk.movieawesome.data.remote.api.response.MoviesResponse

interface MovieRepository {
    suspend fun getListGenreAsync(): List<Genre>

    suspend fun getListMoviesByCategory(type: String, page: Int): MoviesResponse

    suspend fun getMoviesTrending(): MovieTrendingResponse

//    suspend fun insertLocal(movie: Movie)
//
//    suspend fun deleteMovieLocal(id: String)
}
