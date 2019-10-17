package vn.sunasterisk.movieawesome.data.repository.implement

import vn.sunasterisk.movieawesome.data.api.ApiService
import vn.sunasterisk.movieawesome.data.api.response.GenresResponse
import vn.sunasterisk.movieawesome.data.api.response.MoviesResponse
import vn.sunasterisk.movieawesome.data.repository.MovieRepository

class MovieRepositoryImpl(private val apiService: ApiService) : MovieRepository {

    override suspend fun getListMoviesByCategory(
        type: String,
        page: Int
    ): MoviesResponse {
        return apiService.getListMoviesByCategory(type, page)
    }

    override suspend fun getListGenres(): GenresResponse {
        return apiService.getListGenre()
    }
}
