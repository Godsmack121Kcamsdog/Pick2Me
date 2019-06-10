package com.pick2me.kucherenko.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.pick2me.kucherenko.app.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class PickApplication : Application(), HasActivityInjector, Application.ActivityLifecycleCallbacks {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
        AppInjector.init(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }

    override fun onActivityPaused(p0: Activity?) {

    }

    override fun onActivityResumed(p0: Activity?) {

    }

    override fun onActivityStarted(p0: Activity?) {

    }

    override fun onActivityDestroyed(p0: Activity?) {

    }

    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {

    }

    override fun onActivityStopped(p0: Activity?) {

    }

    override fun onActivityCreated(p0: Activity?, p1: Bundle?) {

    }
}