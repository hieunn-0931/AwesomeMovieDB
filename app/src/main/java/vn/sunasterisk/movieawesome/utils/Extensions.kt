package vn.sunasterisk.movieawesome.utils

import vn.sunasterisk.movieawesome.BuildConfig

fun Exception.safeLog() {
    if (BuildConfig.DEBUG) printStackTrace()
}
