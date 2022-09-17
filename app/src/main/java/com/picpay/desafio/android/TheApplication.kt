package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.AppComponent
import com.picpay.desafio.android.di.DaggerAppComponent

class TheApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

}