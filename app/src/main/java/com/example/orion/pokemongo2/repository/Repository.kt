package com.example.productcontaner.repository

import com.example.productcontaner.api.ApiBuilder.retrofitBuilder
import com.example.productcontaner.model.data

class Repository {
    suspend fun getCall() : retrofit2.Response<data>{
        return retrofitBuilder.getUsers()
    }
}