package com.pick2me.kucherenko.app.di.modules

import com.pick2me.kucherenko.app.api.UserService
import com.pick2me.kucherenko.app.repositories.UserRepository
import com.pick2me.kucherenko.app.repositories.legacy.UserRepositoryLegacy
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    internal fun provideUserRepository(userService: UserService): UserRepository {
        return UserRepositoryLegacy(userService)
    }

}