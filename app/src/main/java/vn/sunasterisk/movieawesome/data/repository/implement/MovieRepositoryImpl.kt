package vn.sunasterisk.movieawesome.data.repository.implement

import vn.sunasterisk.movieawesome.data.entity.Genre
import vn.sunasterisk.movieawesome.data.entity.Movie
import vn.sunasterisk.movieawesome.data.local.MovieDao
import vn.sunasterisk.movieawesome.data.remote.api.ApiService
import vn.sunasterisk.movieawesome.data.remote.api.response.MovieTrendingResponse
import vn.sunasterisk.movieawesome.data.remote.api.response.MoviesResponse
import vn.sunasterisk.movieawesome.data.repository.MovieRepository

class MovieRepositoryImpl(private val apiService: ApiService) :
    MovieRepository {
//    override suspend fun insertLocal(movie: Movie) {
//        return movieDao.insert(movie)
//    }
//
//    override suspend fun deleteMovieLocal(id: String) {
//        return movieDao.deleteMovie(id)
//    }
//
    override suspend fun getMoviesTrending(): MovieTrendingResponse {
        return apiService.getMoviesTrendingByDay()
    }

    override suspend fun getListMoviesByCategory(
        type: String,
        page: Int
    ): MoviesResponse {
        return apiService.getListMoviesByCategory(type, page)
    }

    override suspend fun getListGenreAsync(): List<Genre> {
        return apiService.getListGenreAsync().await().genres.map {
            Genre(
                it.id,
                it.name
            )
        }
    }
}
