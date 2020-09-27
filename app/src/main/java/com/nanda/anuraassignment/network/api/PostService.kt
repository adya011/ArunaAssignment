package com.nanda.anuraassignment.network.api

import com.nanda.anuraassignment.model.PostData
import retrofit2.http.GET

interface PostService {
    @GET("/posts")
    suspend fun getPosts(): List<PostData>
}