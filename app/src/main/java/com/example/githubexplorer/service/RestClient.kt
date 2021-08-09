package com.example.githubexplorer.service

import android.util.Patterns.IP_ADDRESS
import com.example.githubexplorer.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {

    private const val TAG = "RetrofitClientService"
    private val okHttpclient: OkHttpClient.Builder = OkHttpClient.Builder()
    private var appRetrofitInstance: ApiServices? = null
    private const val IP_ADDRESS="https://api.github.com/"

    private var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    fun retrofit(ipAddress: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ipAddress)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpclient.build())
            .build()
    }

    fun getRetrofit(): ApiServices {
        if (appRetrofitInstance == null) {
            appRetrofitInstance =
                retrofit(IP_ADDRESS).create<ApiServices>(ApiServices::class.java)
        }
        return appRetrofitInstance!!
    }
}