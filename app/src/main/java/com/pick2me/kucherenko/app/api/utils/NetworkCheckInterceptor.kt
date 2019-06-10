package com.pick2me.kucherenko.app.api.utils

import com.pick2me.kucherenko.app.R
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkCheckInterceptor(private val networkChecker: NetworkChecker) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        if (!networkChecker.isConnected) {
            throw RuntimeException(networkChecker.context.getString(R.string.api_no_network_connection))
        }

        try {
            return chain.proceed(requestBuilder.build())
        } catch (e: SocketTimeoutException) {
            throw RuntimeException()
        } catch (e: UnknownHostException) {
            throw RuntimeException()
        }

    }

}
