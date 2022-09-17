package com.picpay.desafio.android.di

import com.picpay.data.di.ServiceModule
import com.picpay.data.repository.UserRepositoryImpl
import com.picpay.data.service.UserService
import com.picpay.domain.repositories.UserRepository
import com.picpay.domain.usecases.FindAllUsersUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [ServiceModule::class])
class RepositoryModule {

    @Provides
    fun provideUserRepository(userService: UserService): UserRepository {
        return UserRepositoryImpl(userService)
    }

    @Provides
    fun provideFindAllUserUseCase(userService: UserService): FindAllUsersUseCase {
        return FindAllUsersUseCase(provideUserRepository(userService))
    }
}