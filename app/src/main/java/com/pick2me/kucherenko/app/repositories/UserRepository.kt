package com.pick2me.kucherenko.app.repositories

import com.pick2me.kucherenko.app.api.data.PickResponse
import com.pick2me.kucherenko.app.api.data.UserResponse
import io.reactivex.Single

interface UserRepository {

    fun getUsers(page: Int): Single<PickResponse>

    fun getUserProfile(userID: Long): Single<UserResponse>
}