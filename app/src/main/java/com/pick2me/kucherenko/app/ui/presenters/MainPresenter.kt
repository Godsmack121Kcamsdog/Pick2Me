package com.pick2me.kucherenko.app.ui.presenters

import com.arellomobile.mvp.InjectViewState
import com.pick2me.kucherenko.app.db.UsersRepository
import com.pick2me.kucherenko.app.repositories.UserRepository
import com.pick2me.kucherenko.app.ui.views.MainView
import io.reactivex.processors.PublishProcessor

@InjectViewState
class MainPresenter : BasePresenter<MainView>() {

    private lateinit var repository: UserRepository
    private lateinit var dbRepository: UsersRepository
    private val paginator = PublishProcessor.create<Int>()
    private var currentPage = 1
    private var loading = false

    fun attachRepository(repository: UserRepository) {
        this.repository = repository
    }

    fun attachDataBaseRepository(repository: UsersRepository) {
        dbRepository = repository
    }

    fun userInfo(id: Long) {
        mCompositeDisposable.add(
            repository.getUserProfile(id).subscribe({
                viewState.userInfoLoaded(it.body)
            },
                {
                    viewState.showError(it.message!!)
                    dbRepository.findById(id.toInt()).subscribe(
                        viewState::userInfoLoaded
                    ) { err -> viewState.showError(err.message!!) }
                }
            )
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
                    it.data.forEach(dbRepository::insertUser)
                    loading = false
                },
                {
                    viewState.showError(it.message!!)
                    viewState.onError()
                    getUsers()
                }
            )
        mCompositeDisposable.add(disposable)
        paginator.onNext(currentPage)

    }

    fun nextPage() {
        currentPage++
        paginator.onNext(currentPage)
    }

    fun getUsers() {
        mCompositeDisposable.add(
            dbRepository.getAllUsers()
                .subscribe({
                    if (it.isNotEmpty())
                        viewState.onUsersloaded(it)
                }, {
                    viewState.showError(it.message!!)
                }
                )
        )
    }
}