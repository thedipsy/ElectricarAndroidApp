package com.electricarandroidapp.model.interfaces

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "http://10.0.2.2:9091/"

    private val client = OkHttpClient.Builder().build()

    private val retrofit: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(client)
            .build()
    }

    val apiService: ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }

}