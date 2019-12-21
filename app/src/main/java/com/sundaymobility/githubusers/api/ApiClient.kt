package com.example.kotlinprac.api

import android.app.Application
import android.os.Build
import com.FlavorConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.sundaymobility.githubusers.BuildConfig
import com.sundaymobility.githubusers.api.ApiConstants
import com.sundaymobility.githubusers.api.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {


    companion object {
        private lateinit var webService: ApiService

        public fun getClient(application: Application): ApiService {

            val flavorConfig = FlavorConfig()

            if (!::webService.isInitialized) {

                val clientBuilder = OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor { chain ->
                        val requestBuilder = chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json; charset=utf-8")
//                    .addHeader("Authorization", "Bearer " + session.getAccessToken())
                            .addHeader("Accept", "application/json")
                            .addHeader("app-ver-code", BuildConfig.VERSION_CODE.toString())
                            .addHeader("device-sdk_ver", Build.VERSION.SDK_INT.toString())
                            .addHeader("device-type", "android-user")
                            .addHeader("manufacturer", Build.MANUFACTURER)
                            .addHeader("model", Build.MODEL)


                        chain.proceed(requestBuilder.build())

                    }


                flavorConfig.addHttpLoggingInterceptor(clientBuilder)

                val client = clientBuilder.build()


                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(ApiConstants.url)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .client(client)
                    .build()



                webService = retrofit.create(ApiService::class.java)

                return webService
            }
            return webService


        }

    }


}