package com.electricarandroidapp.data.models

data class Car(
    val id: Long,
    val carModel: String,
    val carPlate: String,
    val user: User
)