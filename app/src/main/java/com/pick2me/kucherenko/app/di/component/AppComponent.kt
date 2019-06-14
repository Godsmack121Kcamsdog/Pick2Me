package com.pick2me.kucherenko.app.di.component

import android.content.Context
import com.pick2me.kucherenko.app.PickApplication
import com.pick2me.kucherenko.app.di.modules.ActivityModule
import com.pick2me.kucherenko.app.di.modules.ApiModule
import com.pick2me.kucherenko.app.di.modules.DBModule
import com.pick2me.kucherenko.app.di.modules.DataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ApiModule::class, DataModule::class, ActivityModule::class, DBModule::class])
interface AppComponent : AndroidInjector<PickApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent

    }

    override fun inject(app: PickApplication)

}