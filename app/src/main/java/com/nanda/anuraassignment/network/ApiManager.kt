package com.nanda.anuraassignment.network

import com.nanda.anuraassignment.network.api.PostService
import retrofit2.Retrofit

class ApiManager constructor(private val retrofit: Retrofit) {
    val postService by lazy { retrofit.createApi<PostService>() }
}

inline fun <reified T> Retrofit.createApi(): T = this.create(T::class.java)