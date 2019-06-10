package com.pick2me.kucherenko.app.ui.activities

import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.pick2me.kucherenko.app.R
import com.pick2me.kucherenko.app.api.data.UsersBody
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

    lateinit var adapter: UsersAdapter

    private val VISIBLE_THRESHOLD = 1

    override fun getContainer(): Int = R.layout.activity_main

    override fun showCancelableProgressPagination() {}

    override fun hideCancelableProgress() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attachRepository(repository)

        adapter = UsersAdapter()
        recycler_view.adapter = adapter

        adapter.setListener(this)
        setUpLoadMoreListener()

        presenter.subscribeForUsersRequest()
    }

    override fun onUsersloaded(list: List<UsersBody>) {
        adapter.setData(list.map { user -> UsersAdapter.UserDH(user) }.toMutableList())
    }

    override fun clickUser(id:Long) {
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
}
