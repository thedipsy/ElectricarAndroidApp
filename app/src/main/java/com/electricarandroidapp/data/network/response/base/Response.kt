package com.electricarandroidapp.repository.base

import com.electricarandroidapp.data.network.response.base.Status
import retrofit2.Response

data class ApiResponse<T> (
    val status: Status,
    val data: Response<T>?,
    val exception: Exception?){

    companion object{
        fun <T> success(data: Response<T>) : ApiResponse<T>
            = ApiResponse(Status.Success, data, null)

        fun <T> failure(exception: Exception) : ApiResponse<T>
            = ApiResponse(Status.Failure, null, exception)
    }

    val failed: Boolean
        get() = this.status == Status.Failure

    val succeeded: Boolean
        get() = !failed && this.data?.isSuccessful == true

    val body: T
        get() = this.data!!.body()!!

}