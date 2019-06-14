package com.pick2me.kucherenko.app.api.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "users")
data class UsersBody(

    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("first_name")
    @Expose
    val firstName: String,
    @SerializedName("last_name")
    @Expose
    val lastName: String,
    @SerializedName("avatar")
    @Expose
    val avatar: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var itemId: Int? = null

    override fun toString(): String {
        return "User (name='$firstName', surname=$lastName, email='$email', avatar='$avatar')"
    }
}