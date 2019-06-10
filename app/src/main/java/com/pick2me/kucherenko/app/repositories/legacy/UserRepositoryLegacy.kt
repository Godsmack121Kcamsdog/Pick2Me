package com.pick2me.kucherenko.app.repositories.legacy

import com.pick2me.kucherenko.app.api.UserService
import com.pick2me.kucherenko.app.api.data.PickResponse
import com.pick2me.kucherenko.app.api.data.UserResponse
import com.pick2me.kucherenko.app.api.data.UsersBody
import com.pick2me.kucherenko.app.repositories.UserRepository
import com.pick2me.kucherenko.app.utils.applyIOScheduler
import com.pick2me.kucherenko.app.utils.applySingleScheduler
import io.reactivex.Single

class UserRepositoryLegacy(private val mUserService: UserService) : UserRepository {

    override fun getUsers(page: Int): Single<PickResponse> {
        return mUserService.getUsers(page).applySingleScheduler()
    }

    override fun getUserProfile(userID: Long): Single<UserResponse> {
        return mUserService.getUserProfile(userID).applyIOScheduler()
    }
}