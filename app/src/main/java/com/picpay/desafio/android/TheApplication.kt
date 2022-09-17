package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.ApplicationComponent
import com.picpay.desafio.android.di.DaggerApplicationComponent

class TheApplication : Application() {
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}