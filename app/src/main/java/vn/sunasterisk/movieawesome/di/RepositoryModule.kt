package vn.sunasterisk.movieawesome.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import org.koin.dsl.module
import vn.sunasterisk.movieawesome.data.repository.MovieRepository
import vn.sunasterisk.movieawesome.data.repository.implement.MovieRepositoryImpl

val repositoryModule = module {
    single { Gson() }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}
