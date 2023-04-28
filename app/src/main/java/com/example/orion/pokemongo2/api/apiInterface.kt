package com.example.productcontaner.api

import com.example.productcontaner.model.data
import retrofit2.http.GET

interface apiInterface {
    @GET("products")
   suspend  fun getUsers(): retrofit2.Response<data>
}