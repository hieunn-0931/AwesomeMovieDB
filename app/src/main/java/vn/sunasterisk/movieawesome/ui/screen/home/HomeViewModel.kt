package vn.sunasterisk.movieawesome.ui.screen.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vn.sunasterisk.movieawesome.data.entity.Movie
import vn.sunasterisk.movieawesome.data.entity.MovieTrending
import vn.sunasterisk.movieawesome.data.repository.MovieRepository
import vn.sunasterisk.movieawesome.ui.base.viewmodel.BaseLoadMoreRefreshViewModel
import vn.sunasterisk.movieawesome.ui.screen.viewpager.SlideAdapter

class HomeViewModel(private val movieRepository: MovieRepository) :
    BaseLoadMoreRefreshViewModel<Movie>() {

    override fun loadData(page: Int) {
        viewModelScope.launch {
            try {
                onLoadSuccess(
                    page,
                    movieRepository.getListMoviesByCategory("upcoming", page).movies
                )
            } catch (e: Exception) {
                onLoadFail(e)
            }
        }
    }
}
