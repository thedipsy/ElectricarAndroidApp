package com.electricarandroidapp.data.network.response

data class LoginResponse(
    val jwtToken: String,
    val username: String
)