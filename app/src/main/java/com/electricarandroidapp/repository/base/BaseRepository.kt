package com.electricarandroidapp.repository.base

import retrofit2.Response
import java.lang.Exception

abstract class BaseRepository {

    inline fun <T> safeApiCall(apiCall: () -> Response<T>) : ApiResponse<T>
        = try {
            ApiResponse.success(apiCall.invoke())
        } catch (e: Exception){
            ApiResponse.failure(e)
        }

}