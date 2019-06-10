package com.pick2me.kucherenko.app.di.modules

import android.content.Context
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.pick2me.kucherenko.app.BuildConfig
import com.pick2me.kucherenko.app.api.UserService
import com.pick2me.kucherenko.app.api.utils.NetworkCheckInterceptor
import com.pick2me.kucherenko.app.api.utils.NetworkChecker
import com.pick2me.kucherenko.app.utils.GsonUtils
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {


    @Provides
    @Singleton
    internal fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }


    internal var gson = GsonUtils.createGson()


    private val host: String = BuildConfig.HOST_DEBUG


    private val hostStage = "http://stage.bananacar.lv/"


    @Provides
    @Singleton
    internal fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(host)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(networkChecker: NetworkChecker): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(OkHttpProfilerInterceptor())
            .addInterceptor(NetworkCheckInterceptor(networkChecker))
            .addNetworkInterceptor(com.facebook.stetho.okhttp3.StethoInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(240, TimeUnit.SECONDS)
            .readTimeout(240, TimeUnit.SECONDS).build()
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    internal fun provideNetworkChecker(context: Context): NetworkChecker {
        return NetworkChecker(context)
    }

    @Provides
    @Singleton
    internal fun provideGson() = gson

}