package com.nanda.anuraassignment.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nanda.anuraassignment.model.PostDao
import com.nanda.anuraassignment.model.PostData

private const val DB_NAME = "PostDB"

@Database(
    entities = [
        PostData::class
    ], exportSchema = false, version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPostDao(): PostDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, DB_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}