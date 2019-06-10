package com.pick2me.kucherenko.app.ui.presenters

import com.arellomobile.mvp.InjectViewState
import com.pick2me.kucherenko.app.repositories.UserRepository
import com.pick2me.kucherenko.app.ui.views.MainView
import io.reactivex.processors.PublishProcessor


@InjectViewState
class MainPresenter : BasePresenter<MainView>() {

    private lateinit var repository: UserRepository
    private val paginator = PublishProcessor.create<Int>()
    private var currentPage = 1
    private var loading = false

    fun attachRepository(repository: UserRepository) {
        this.repository = repository
    }


    fun userInfo(id: Long) {
        mCompositeDisposable.add(
            repository.getUserProfile(id).subscribe({
                viewState.showToast(it.body.firstName)
            },
                { viewState.showError(it.message!!) })
        )
    }

    fun subscribeForUsersRequest() {
        val disposable = paginator
            .onBackpressureDrop()
            .doOnNext {
                loading = true
            }
            .concatMapSingle { repository.getUsers(currentPage) }
            .subscribe(
                {
                    viewState.onUsersloaded(it.data)
                    loading = false
                },
                { viewState.showError(it.message!!) }
            )
        mCompositeDisposable.add(disposable)
        paginator.onNext(currentPage)

    }

    fun nextPage() {
        currentPage++
        paginator.onNext(currentPage)
    }

}