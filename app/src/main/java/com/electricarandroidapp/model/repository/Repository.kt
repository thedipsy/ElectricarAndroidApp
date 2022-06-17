package com.electricarandroidapp.model.repository

import com.electricarandroidapp.model.interfaces.ApiService


class Repository(private val apiService: ApiService) {
    fun getCars() = apiService.getCars()
}