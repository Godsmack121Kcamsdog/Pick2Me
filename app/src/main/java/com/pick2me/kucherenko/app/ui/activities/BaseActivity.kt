package com.pick2me.kucherenko.app.ui.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.google.gson.Gson
import com.pick2me.kucherenko.app.db.LocalStorage
import com.pick2me.kucherenko.app.repositories.UserRepository
import com.pick2me.kucherenko.app.ui.views.BaseView
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : MvpAppCompatActivity(), BaseView, HasSupportFragmentInjector {


    private val TAG = "BaseActivity"

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

//    @Inject
//    lateinit var mLocalStorage: LocalStorage

    @Inject
    lateinit var mGson: Gson

    protected abstract fun getContainer(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun showError(message: String) {
        showToast("Error: $message")
        Log.e(TAG, message + "")
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showToast(messageRes: Int) {
        Toast.makeText(this, getString(messageRes), Toast.LENGTH_LONG).show()
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    override fun showCancelableProgressMain() {
    }

    override fun showCancelableProgressPagination() {
    }

    override fun hideCancelableProgress() {
    }
}
