package com.example.mvvm.data.network

import com.example.mvvm.data.service.ApiServiceInterface
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/*
* Creado por Francisco Nolasco
* Año 2023 | 2024
* */
object InstanceRetrofit {

    private const val URL_BASE = "http://10.0.2.2/api-libros/endp/"


    val retrofitService : ApiServiceInterface by lazy {
        getRetrofit().create(ApiServiceInterface::class.java)
    }

    private fun getRetrofit() : Retrofit = Retrofit
        .Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val gson = GsonBuilder()
        .setLenient() // Hace que Gson sea más tolerante con los JSON mal formados
        .create()
}