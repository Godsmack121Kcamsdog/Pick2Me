package com.pick2me.kucherenko.app.di.modules

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import android.arch.persistence.room.Room
import android.content.Context
import com.pick2me.kucherenko.app.db.UsersDao
import com.pick2me.kucherenko.app.db.UsersDatabase


@Module
class DBModule {

    @Singleton
    @Provides
    fun provideUserDatabase(context: Context): UsersDatabase {
        return Room.databaseBuilder(
            context,
            UsersDatabase::class.java, UsersDatabase.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUsersDao(db: UsersDatabase): UsersDao {
        return db.usersDao()
    }
}