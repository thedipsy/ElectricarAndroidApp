package com.electricarandroidapp.viewmodel.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electricarandroidapp.data.network.request.RegisterRequest
import com.electricarandroidapp.data.network.response.RegisterResponse
import com.electricarandroidapp.repository.RegisterRepository
import com.electricarandroidapp.repository.base.ApiResponse
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository : RegisterRepository) : ViewModel() {

    private val _registerResponse : MutableLiveData<ApiResponse<RegisterResponse>> = MutableLiveData()
    val registerResponse : MutableLiveData<ApiResponse<RegisterResponse>>
        get() = _registerResponse

    fun registerUser(email: String, password: String, name: String, surname: String, phone: String)
     = viewModelScope.launch {
        val request = RegisterRequest(email, password, name, surname, phone)
        val response = repository.register(request)
        _registerResponse.postValue(response)
    }

}