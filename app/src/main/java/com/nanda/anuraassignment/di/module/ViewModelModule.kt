package com.nanda.anuraassignment.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nanda.anuraassignment.di.ViewModelProviderFactory
import com.nanda.anuraassignment.di.scope.ViewModelKey
import com.nanda.anuraassignment.ui.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    @Singleton
    abstract fun provideViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun providerMainViewModel(mainViewModel: MainViewModel): ViewModel

}