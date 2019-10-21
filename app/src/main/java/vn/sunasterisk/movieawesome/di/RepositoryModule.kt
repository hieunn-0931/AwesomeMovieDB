package vn.sunasterisk.movieawesome.di

import com.google.gson.Gson
import org.koin.dsl.module
import vn.sunasterisk.movieawesome.data.repository.MovieRepository
import vn.sunasterisk.movieawesome.data.repository.implement.MovieRepositoryImpl

val repositoryModule = module {
    single { Gson() }
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
}
