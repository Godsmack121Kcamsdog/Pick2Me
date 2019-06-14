package com.pick2me.kucherenko.app.ui.views

import com.arellomobile.mvp.MvpView

interface BaseView : MvpView {

    fun showError(message: String)

    fun showToast(message: String)

    fun showToast(messageRes: Int)

    fun showCancelableProgressMain()

    fun showCancelableProgressPagination()

    fun hideCancelableProgress()

    companion object {
        val TRUE: Byte = 1
        val FALSE: Byte = 0
    }

    fun onError()
}
