package com.pick2me.kucherenko.app.db

import com.pick2me.kucherenko.app.api.data.UsersBody
import com.pick2me.kucherenko.app.utils.applyIOScheduler
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepository @Inject constructor(private val usersDao: UsersDao) {

    fun getAllUsers(): Single<List<UsersBody>> {
        return usersDao.getAll().applyIOScheduler()
    }

    fun insertUser(user: UsersBody) {
        Completable.fromAction { usersDao.insert(user) }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun removeUser(user: UsersBody) {
        Completable.fromAction { usersDao.delete(user) }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun findById(id:Int):Single<UsersBody>{
        return usersDao.findById(id).applyIOScheduler()
    }

}