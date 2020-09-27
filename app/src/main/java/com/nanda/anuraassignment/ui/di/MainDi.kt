package com.nanda.anuraassignment.ui.di

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.nanda.anuraassignment.ui.contract.MainContract
import com.nanda.anuraassignment.ui.view.MainActivity
import com.nanda.anuraassignment.ui.controller.MainController
import com.nanda.anuraassignment.ui.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class MainDi {

    @Provides
    fun providesMainViewModel(
        activity: MainActivity,
        factory: ViewModelProvider.Factory
    ): MainContract.ViewModel =
        ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)

    @Provides
    fun providesMainController(
        mainActivity: MainActivity,
        mainViewmodel: MainContract.ViewModel
    ): MainContract.Controller =
        MainController(
            mainActivity as LifecycleOwner,
            mainActivity as MainContract.View,
            mainViewmodel
        )
}