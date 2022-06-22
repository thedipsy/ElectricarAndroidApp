package com.electricarandroidapp.repository

import com.electricarandroidapp.api.ApiService


class Repository(private val apiService: ApiService) {

    fun getCars() = apiService.getCars()

}