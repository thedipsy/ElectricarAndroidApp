package com.electricarandroidapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.electricarandroidapp.repository.LoginRepository
import com.electricarandroidapp.repository.RegisterRepository
import com.electricarandroidapp.repository.base.BaseRepository
import com.electricarandroidapp.viewmodel.login.LoginViewModel
import com.electricarandroidapp.viewmodel.login.RegisterViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory (
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(repository as LoginRepository) as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> RegisterViewModel(repository as RegisterRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}
