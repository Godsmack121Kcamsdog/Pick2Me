package com.pick2me.kucherenko.app.di.modules

import com.pick2me.kucherenko.app.ui.activities.MainActivity
import com.pick2me.kucherenko.app.di.scopes.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [BuildersModuleMainActivity::class])
    @ActivityScope
    internal abstract fun mainActivity(): MainActivity
}