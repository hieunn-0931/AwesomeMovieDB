package vn.sunasterisk.movieawesome.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import vn.sunasterisk.movieawesome.ui.screen.favorite.FavoriteViewModel
import vn.sunasterisk.movieawesome.ui.screen.home.HomeViewModel
import vn.sunasterisk.movieawesome.ui.screen.home.SlideViewModel
import vn.sunasterisk.movieawesome.ui.screen.main.MainViewModel

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { SlideViewModel(get()) }
    viewModel { FavoriteViewModel() }
}
