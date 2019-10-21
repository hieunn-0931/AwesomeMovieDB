package vn.sunasterisk.movieawesome.ui.screen.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vn.sunasterisk.movieawesome.data.entity.MovieTrending
import vn.sunasterisk.movieawesome.data.repository.MovieRepository
import vn.sunasterisk.movieawesome.ui.base.viewmodel.BaseViewModel

class SlideViewModel(private val movieRepository: MovieRepository) :
    BaseViewModel() {

    val moviesTrending = MutableLiveData<List<MovieTrending>>()

    fun loadData() {
        viewModelScope.launch {
            moviesTrending.value = movieRepository.getMoviesTrending().moviesTrending
        }
    }
}
