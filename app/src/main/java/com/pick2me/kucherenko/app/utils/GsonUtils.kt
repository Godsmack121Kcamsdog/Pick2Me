package com.pick2me.kucherenko.app.utils

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

object GsonUtils {

    fun createGson() = GsonBuilder()
            .setLenient()
            .excludeFieldsWithoutExposeAnnotation()
            .serializeNulls()
            .create()
}