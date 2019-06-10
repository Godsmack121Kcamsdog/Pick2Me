package com.pick2me.kucherenko.app.di.modules

import com.pick2me.kucherenko.app.ui.DetailedFragment
import com.pick2me.kucherenko.app.di.scopes.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModuleMainActivity {

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributeDetailedFragment(): DetailedFragment

}
