package vn.sunasterisk.movieawesome.data.repository

import vn.sunasterisk.movieawesome.data.api.response.GenresResponse
import vn.sunasterisk.movieawesome.data.api.response.MoviesResponse

interface MovieRepository {
    suspend fun getListGenres(): GenresResponse

    suspend fun getListMoviesByCategory(type: String, page: Int): MoviesResponse
}
