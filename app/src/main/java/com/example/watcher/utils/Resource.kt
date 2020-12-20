package com.example.watcher.utils

sealed class Resource<T>(val data: T? = null, val message: String? = null) { // class to wrap around network responses , sealed is kind of abstract but classes defined to inherit from this
    class Succes<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}
