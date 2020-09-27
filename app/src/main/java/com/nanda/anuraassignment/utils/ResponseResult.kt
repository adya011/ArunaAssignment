package com.nanda.anuraassignment.utils

import java.lang.Exception

sealed class ResponseResult<out V, out E> {

    data class Success<out V>(val value: V) : ResponseResult<V, Nothing>()
    data class Error<out E>(val error: E) : ResponseResult<Nothing, E>()

    companion object {
        inline fun <V> build(function: () -> V): ResponseResult<V, Exception> =
            try {
                Success(function.invoke())
            } catch (e: Exception) {
                Error(e)
            }
    }
}