package com.pick2me.kucherenko.app.api.utils

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

@Suppress("DEPRECATION")
class NetworkChecker @Inject
constructor(val context: Context) {

    val isConnected: Boolean
        get() {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }

}
