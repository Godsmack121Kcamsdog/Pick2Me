
package com.pick2me.kucherenko.app.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.pick2me.kucherenko.app.PickApplication
import com.pick2me.kucherenko.app.di.component.DaggerAppComponent

import dagger.android.support.AndroidSupportInjection

/**
 * Helper class to automatically inject fragments if they implement [Injectable].
 */
object AppInjector {
    fun init(application: PickApplication) {
        DaggerAppComponent.builder()
                .context(application)
                .build()
                .inject(application)
        application
                .registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {

                    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                        handleActivity(activity)
                    }

                    override fun onActivityStarted(activity: Activity) {

                    }

                    override fun onActivityResumed(activity: Activity) {

                    }

                    override fun onActivityPaused(activity: Activity) {

                    }

                    override fun onActivityStopped(activity: Activity) {

                    }

                    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

                    }

                    override fun onActivityDestroyed(activity: Activity) {

                    }
                })
    }

    private fun handleActivity(activity: Activity) {
        if (activity is FragmentActivity) {
            activity.supportFragmentManager
                    .registerFragmentLifecycleCallbacks(
                            object : FragmentManager.FragmentLifecycleCallbacks() {
                                override fun onFragmentCreated(fm: FragmentManager, f: Fragment,
                                                               savedInstanceState: Bundle?) {
                                    if (f is Injectable) {
                                        AndroidSupportInjection.inject(f)
                                    }
                                }
                            }, true)
        }
    }
}
