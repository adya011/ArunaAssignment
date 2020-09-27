package com.nanda.anuraassignment.di.module

import android.app.Application
import com.nanda.anuraassignment.model.PostDao
import com.nanda.anuraassignment.utils.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        AppDatabase.getInstance(app.applicationContext)

    @Provides
    @Singleton
    fun providePostDao(db: AppDatabase): PostDao = db.getPostDao()
}