package com.nanda.anuraassignment.network.repository

import com.nanda.anuraassignment.model.PostData
import com.nanda.anuraassignment.network.ApiManager
import com.nanda.anuraassignment.utils.ResponseResult
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val api: ApiManager) {
    suspend fun getPost(): ResponseResult<List<PostData>, Exception> {
        return try {
            val response = api.postService.getPosts()
            ResponseResult.build { response }
        } catch (e: Exception) {
            ResponseResult.build { throw e }
        }
    }
}