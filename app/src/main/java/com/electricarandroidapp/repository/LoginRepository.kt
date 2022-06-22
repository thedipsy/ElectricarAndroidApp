package com.electricarandroidapp.repository

import com.electricarandroidapp.api.ApiService
import com.electricarandroidapp.data.network.request.LoginRequest
import com.electricarandroidapp.data.network.response.LoginResponse
import com.electricarandroidapp.repository.base.ApiResponse
import com.electricarandroidapp.repository.base.BaseRepository

class LoginRepository(private val apiService: ApiService) : BaseRepository() {

    suspend fun login(loginRequest: LoginRequest) : ApiResponse<LoginResponse>
        = safeApiCall { apiService.login(loginRequest) }

}