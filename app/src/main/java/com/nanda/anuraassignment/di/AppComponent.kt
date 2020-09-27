package com.nanda.anuraassignment.di

import android.app.Application
import androidx.room.Database
import com.nanda.anuraassignment.App
import com.nanda.anuraassignment.di.module.ActivityModule
import com.nanda.anuraassignment.di.module.DatabaseModule
import com.nanda.anuraassignment.di.module.NetworkModule
import com.nanda.anuraassignment.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules =
    [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}