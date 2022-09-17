package com.picpay.desafio.android.di

import android.app.Application
import com.picpay.data.di.CacheModule
import com.picpay.data.di.NetworkModule
import com.picpay.data.di.ServiceModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        CacheModule::class,
        NetworkModule::class,
        ServiceModule::class,
        RepositoryModule::class,
        ContextModule::class]
)
interface AppComponent {
    fun viewModelsFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}


