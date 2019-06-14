package com.pick2me.kucherenko.app.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.pick2me.kucherenko.app.api.data.UsersBody

@Database(entities = [UsersBody::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun usersDao(): UsersDao

    companion object {
        const val DB_NAME = "com.android.pick2me.app.db"
    }
}