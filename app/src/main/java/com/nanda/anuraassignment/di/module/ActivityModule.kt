package com.nanda.anuraassignment.di.module

import com.nanda.anuraassignment.ui.di.MainDi
import com.nanda.anuraassignment.ui.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule{
    @ContributesAndroidInjector(modules = [MainDi::class])
    abstract fun bindTrendingActivty(): MainActivity
}