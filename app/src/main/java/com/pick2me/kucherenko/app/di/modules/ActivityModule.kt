package com.pick2me.kucherenko.app.di.modules

import com.pick2me.kucherenko.app.di.scopes.ActivityScope
import com.pick2me.kucherenko.app.ui.activities.DetailedActivity
import com.pick2me.kucherenko.app.ui.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    @ActivityScope
    internal abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    @ActivityScope
    internal abstract fun detailedActivity(): DetailedActivity
}