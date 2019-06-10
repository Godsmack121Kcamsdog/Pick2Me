package com.pick2me.kucherenko.app.api.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @Expose
    @SerializedName("data") var body: UsersBody
)