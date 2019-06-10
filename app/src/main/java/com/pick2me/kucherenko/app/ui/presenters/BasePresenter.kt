package com.pick2me.kucherenko.app.ui.presenters

import android.util.Log
import com.arellomobile.mvp.MvpPresenter
import com.pick2me.kucherenko.app.ui.views.BaseView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<View : BaseView> : MvpPresenter<View>() {

    protected var mCompositeDisposable = CompositeDisposable()

    protected fun unsubscribeOnDestroy(vararg disposables: Disposable) {
        for (disposable in disposables)
            mCompositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.clear()
        Log.i("BasePresenter", "Disposable dispose " + mCompositeDisposable.size() + " elements")
    }


    fun unsubscribe() {
        mCompositeDisposable.clear()
    }
}
