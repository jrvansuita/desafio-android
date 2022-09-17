package com.picpay.data.di

import android.content.Context
import android.util.Log
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import java.io.File
import java.util.concurrent.TimeUnit

@Module
class CacheModule {

    @Provides
    fun provideCache(context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MB
        val httpCacheDirectory = File(context.cacheDir, "http-cache")
        Log.d("Cache-File", httpCacheDirectory.absolutePath)
        return Cache(httpCacheDirectory, cacheSize.toLong())
    }

    @Provides
    fun provideCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(10, TimeUnit.DAYS)
                .build()

            response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
        }
    }

}