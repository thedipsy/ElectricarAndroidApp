package com.electricarandroidapp.model.interfaces

import com.electricarandroidapp.model.dataClasses.Car
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("cars")
    fun getCars(): Call<List<Car>>

    @POST("login")
    fun login(
        @Field("username") email: String,
        @Field("password") password : String) : Any

}