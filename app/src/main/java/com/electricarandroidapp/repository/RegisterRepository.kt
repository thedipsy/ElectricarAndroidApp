package com.electricarandroidapp.repository

import com.electricarandroidapp.api.ApiService
import com.electricarandroidapp.data.network.request.RegisterRequest
import com.electricarandroidapp.data.network.response.RegisterResponse
import com.electricarandroidapp.repository.base.ApiResponse
import com.electricarandroidapp.repository.base.BaseRepository

class RegisterRepository(private val apiService: ApiService) : BaseRepository() {

    suspend fun register(request: RegisterRequest) : ApiResponse<RegisterResponse>
        = safeApiCall { apiService.register(request) }

}