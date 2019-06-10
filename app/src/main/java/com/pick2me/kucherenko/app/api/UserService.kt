package com.pick2me.kucherenko.app.api

import com.pick2me.kucherenko.app.api.data.PickResponse
import com.pick2me.kucherenko.app.api.data.UserResponse
import com.pick2me.kucherenko.app.api.data.UsersBody
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("api/users?")
    fun getUsers(@Query("page") page: Int): Single<PickResponse>

    @GET("api/users/{path}")
    fun getUserProfile(@Path("path") user: Long): Single<UserResponse>
}