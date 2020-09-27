package com.nanda.anuraassignment.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "post_data")
data class PostData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    @Json(name = "title") val title: String? = null,

    @ColumnInfo(name = "body")
    @Json(name = "body") val body: String? = null
)