package com.electricarandroidapp.data.network.response.base

sealed class Status{
    object Success: Status()
    object Failure: Status()
}