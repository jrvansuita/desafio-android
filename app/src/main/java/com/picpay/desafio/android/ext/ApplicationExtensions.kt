package com.picpay.desafio.android.ext

import android.app.Application
import com.picpay.desafio.android.TheApplication
import com.picpay.desafio.android.di.ApplicationComponent

fun Application.appComponent(): ApplicationComponent {
    return (applicationContext as TheApplication).appComponent
}


