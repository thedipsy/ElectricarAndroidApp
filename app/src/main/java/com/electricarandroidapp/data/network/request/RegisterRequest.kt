package com.electricarandroidapp.data.network.request

data class RegisterRequest(
    val email: String,
    val password: String,
    val name: String,
    val surname: String,
    val phone: String
)