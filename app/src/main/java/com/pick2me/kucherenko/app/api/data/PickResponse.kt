package com.pick2me.kucherenko.app.api.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PickResponse {

    @SerializedName("page")
    @Expose
    var page: Int = 0
    @SerializedName("per_page")
    @Expose
    var perPage: Int? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
    @SerializedName("data")
    @Expose
    var data: MutableList<UsersBody> = mutableListOf()

}