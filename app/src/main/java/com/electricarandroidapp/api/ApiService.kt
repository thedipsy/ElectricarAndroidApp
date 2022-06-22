package com.electricarandroidapp.api

import com.electricarandroidapp.data.models.Car
import com.electricarandroidapp.data.network.request.LoginRequest
import com.electricarandroidapp.data.network.request.RegisterRequest
import com.electricarandroidapp.data.network.response.LoginResponse
import com.electricarandroidapp.data.network.response.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("cars")
    fun getCars(): Call<List<Car>>

    @POST("login")
    suspend fun login(@Body request: LoginRequest) : Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body request: RegisterRequest) : Response<RegisterResponse>
}