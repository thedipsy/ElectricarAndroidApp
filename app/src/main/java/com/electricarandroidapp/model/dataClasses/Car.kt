package com.electricarandroidapp.model.dataClasses

data class Car(
    val id: Long,
    val carModel: String,
    val carPlate: String,
    val user: User
)