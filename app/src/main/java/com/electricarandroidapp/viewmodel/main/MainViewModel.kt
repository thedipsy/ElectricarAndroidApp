package com.electricarandroidapp.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.electricarandroidapp.api.RetrofitInstance
import com.electricarandroidapp.data.models.Car
import com.electricarandroidapp.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: Repository
                    = Repository(RetrofitInstance.apiService)) : ViewModel() {

    private var _carsLiveData = MutableLiveData<List<Car>>()
    val carLiveData:LiveData<List<Car>>
        get() = _carsLiveData

    init {
        fetchCars()
    }

    private fun fetchCars(){
        val client = repository.getCars()

        client.enqueue(object : Callback<List<Car>>{
            override fun onResponse(call: Call<List<Car>>, response: Response<List<Car>>) {
                if(response.isSuccessful){
                    _carsLiveData.postValue(response.body())

                }
            }

            override fun onFailure(call: Call<List<Car>>, t: Throwable) {
                println("failed")
            }

        })
    }

}