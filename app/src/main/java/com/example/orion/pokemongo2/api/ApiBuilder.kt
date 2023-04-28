package com.example.productcontaner.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiBuilder {
            val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fakestoreapi.com/")
            .build()
            .create(apiInterface::class.java)
}