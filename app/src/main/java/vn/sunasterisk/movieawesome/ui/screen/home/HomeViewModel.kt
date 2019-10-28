package vn.sunasterisk.movieawesome.ui.screen.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vn.sunasterisk.movieawesome.data.entity.Movie
import vn.sunasterisk.movieawesome.data.repository.MovieRepository
import vn.sunasterisk.movieawesome.ui.base.viewmodel.BaseLoadMoreRefreshViewModel

class HomeViewModel(private val movieRepository: MovieRepository) :
    BaseLoadMoreRefreshViewModel<Movie>() {

    override fun loadData(page: Int) {
        viewModelScope.launch {
            try {
                onLoadSuccess(
                    page,
                    movieRepository.getListMoviesByCategory("upcoming", page).movies
                )
                movieRepository.getListGenreAsync()
            } catch (e: Exception) {
                onLoadFail(e)
            }
        }
    }
}
