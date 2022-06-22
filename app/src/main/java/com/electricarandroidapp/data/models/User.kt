package com.electricarandroidapp.data.models

import java.util.Collections.emptyList

data class User(
    val name: String,
    val surname: String,
    val phone: String,
    val role: String,
    val username: String,
    var password: String,
    var cars: List<Car> = emptyList()
    //var reservations: List<Reservation> = emptyList()
)