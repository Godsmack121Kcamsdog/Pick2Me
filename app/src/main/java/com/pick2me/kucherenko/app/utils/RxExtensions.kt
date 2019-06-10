package com.pick2me.kucherenko.app.utils

import android.annotation.SuppressLint
import com.pick2me.kucherenko.app.ui.views.BaseView
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun <T> Single<T>.applyIOScheduler() =
        subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.applySingleScheduler() =
    subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread())

@SuppressLint("CheckResult")
fun <T> Single<T>.getCancelableProgress(viewState: BaseView?): Single<T> {
    return compose {
        doOnSubscribe {
            viewState?.showCancelableProgressMain()
        }.doAfterTerminate {
            viewState?.hideCancelableProgress()
        }
    }
}

@SuppressLint("CheckResult")
fun <T> Single<T>.getPaginationProgressTransformer(viewState: BaseView?): Single<T> {
    return compose {
        doOnSubscribe {
            viewState?.showCancelableProgressPagination()
        }.doAfterTerminate {
            viewState?.hideCancelableProgress()
        }
    }
}


