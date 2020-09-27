package com.nanda.anuraassignment.model

import androidx.room.*

@Dao
interface PostDao {
    @get:Query("SELECT * FROM post_data")
    val all: List<PostData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg posts: PostData)

    @Delete
    fun deleteAll(vararg posts: PostData)
}