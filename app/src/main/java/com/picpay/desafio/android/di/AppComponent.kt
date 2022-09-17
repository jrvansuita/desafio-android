package com.picpay.desafio.android.di

import com.picpay.data.di.NetworkModule
import com.picpay.data.di.ServiceModule
import com.picpay.desafio.android.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        NetworkModule::class,
        ServiceModule::class,
        RepositoryModule::class]
)

interface ApplicationComponent {
    fun inject(mainViewModel: MainViewModel)
    fun viewModelsFactory(): ViewModelFactory
}
