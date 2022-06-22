package com.electricarandroidapp.data.network.response

data class RegisterResponse (
    val email: String,
    val password: String,
    val name: String,
    val surname: String,
    val phone: String
)