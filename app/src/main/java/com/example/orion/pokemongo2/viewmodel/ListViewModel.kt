package com.example.productcontaner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productcontaner.repository.Repository
import com.example.productcontaner.model.data
import kotlinx.coroutines.launch
import retrofit2.Response

class ListViewModel : ViewModel() {

    private val myRepository = Repository()

    private val _myResponse : MutableLiveData<Response<data>> = MutableLiveData()
    val myResponse : LiveData<Response<data>>
        get() = _myResponse

    fun getCall(){
        viewModelScope.launch {
            //getting responce
            val response = myRepository.getCall()
            //mathod to take a responce
            _myResponse.value = response
        }
    }

}