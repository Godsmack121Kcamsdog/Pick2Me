package com.pick2me.kucherenko.app.ui.activities

import android.os.Bundle
import com.bumptech.glide.Glide
import com.pick2me.kucherenko.app.R
import com.pick2me.kucherenko.app.api.data.UsersBody
import com.pick2me.kucherenko.app.di.Injectable
import kotlinx.android.synthetic.main.activity_detailed.*

class DetailedActivity : BaseActivity(), Injectable {

    override fun getContainer(): Int = R.layout.activity_detailed

    override fun onError() {
        println("error")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.extras.containsKey("user")) {
            val body: UsersBody = intent.extras.get("user") as UsersBody
            Glide.with(this).load(body.avatar).into(avatar)
            name.text = body.firstName
            surname.text = body.lastName
            email.text = body.email
        }
    }
}