package com.example.technical.challenge.data.base

/**
 * A generic class that holds a value with its loading status.
 *
 * Result is usually created by the Repository classes where they return
 * `LiveData<Result<T>>` to pass back the latest data to the UI with its fetch status.
 */


sealed class ResultWrapper<out T>{
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class ERROR(val value: Errors): ResultWrapper<Nothing>()

}

sealed class Errors{
    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null): Errors()
    object NetworkError: Errors()
    object NotSure: Errors()
    // for internet connectivity disability
    object InternetConnectionError: Errors()
}