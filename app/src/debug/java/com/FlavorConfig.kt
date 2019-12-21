package com

import android.app.Application

import okhttp3.OkHttpClient

public class FlavorConfig {


    public fun addHttpLoggingInterceptor(clientBuilder: OkHttpClient.Builder) {
        val logRequestResponseInterceptor = okhttp3.logging.HttpLoggingInterceptor()
        logRequestResponseInterceptor.setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.BODY)
        clientBuilder.addInterceptor(logRequestResponseInterceptor)
        clientBuilder.addNetworkInterceptor(com.facebook.stetho.okhttp3.StethoInterceptor())

    }

    public fun initStetho(application: Application) {

        com.facebook.stetho.Stetho.initializeWithDefaults(application)
    }


}
