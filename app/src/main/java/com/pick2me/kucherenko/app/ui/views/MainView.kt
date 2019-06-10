package com.pick2me.kucherenko.app.ui.views

import com.pick2me.kucherenko.app.api.data.UsersBody

interface MainView : BaseView {

    fun onUsersloaded(list:List<UsersBody>)
}