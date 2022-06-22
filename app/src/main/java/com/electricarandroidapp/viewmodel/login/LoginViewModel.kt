package com.electricarandroidapp.viewmodel.login

import androidx.lifecycle.*
import com.electricarandroidapp.data.network.request.LoginRequest
import com.electricarandroidapp.data.network.response.LoginResponse
import com.electricarandroidapp.repository.LoginRepository
import com.electricarandroidapp.repository.base.ApiResponse
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    //we do not want other layers to change the response, that's why we keep mutablelivedata for storing and livedata for getting
    private val _loginResponse: MutableLiveData<ApiResponse<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<ApiResponse<LoginResponse>>
        get() = _loginResponse


    fun login(email: String, password: String)
    = viewModelScope.launch {//function for corountine is making the call on IO instead of on the main thread

        val loginRequest = LoginRequest(email, password)
        val loginResponse = repository.login(loginRequest)

        _loginResponse.postValue(loginResponse)
    }


    sealed class Event {
        object NavigateToRegisterScreen : Event()

    }
}