package com.pick2me.kucherenko.app.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.pick2me.kucherenko.app.R
import com.pick2me.kucherenko.app.api.data.UsersBody
import com.pick2me.kucherenko.app.db.UsersRepository
import com.pick2me.kucherenko.app.repositories.UserRepository
import com.pick2me.kucherenko.app.ui.adapters.UsersAdapter
import com.pick2me.kucherenko.app.ui.presenters.MainPresenter
import com.pick2me.kucherenko.app.ui.views.MainView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView, UsersAdapter.UserListener {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var repository: UserRepository

    @Inject
    lateinit var dbRepository: UsersRepository

    lateinit var adapter: UsersAdapter

    private val VISIBLE_THRESHOLD = 1

    override fun getContainer(): Int = R.layout.activity_main

    override fun showCancelableProgressPagination() {}

    override fun hideCancelableProgress() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.attachRepository(repository)
        presenter.attachDataBaseRepository(dbRepository)

        adapter = UsersAdapter()
        recycler_view.adapter = adapter

        adapter.setListener(this)

        setUpLoadMoreListener()
        setUpRefreshListener()

        presenter.subscribeForUsersRequest()
    }

    override fun onUsersloaded(list: List<UsersBody>) {
        swiper.isRefreshing = false
        adapter.setData(list.map { user -> UsersAdapter.UserDH(user) }.toMutableList())
    }

    override fun clickUser(id: Long) {
        presenter.userInfo(id)
    }

    private fun setUpLoadMoreListener() {
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = recycler_view.layoutManager!!.itemCount
                val lastVisibleItem = (recycler_view.layoutManager as LinearLayoutManager)
                    .findLastVisibleItemPosition()

                if (totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)) {
                    presenter.nextPage()
                }

            }
        })
    }

    private fun setUpRefreshListener() {
        swiper.setOnRefreshListener { presenter.subscribeForUsersRequest() }
    }

    override fun onError() {
        if (swiper.isRefreshing) swiper.isRefreshing = false
    }

    override fun userInfoLoaded(body: UsersBody) {
        val userIntent = Intent(this, DetailedActivity().javaClass)
        userIntent.putExtra("user", body)
    }

}
