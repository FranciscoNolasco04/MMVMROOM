package com.example.mvvm.data.service

import android.util.Log
import com.example.mvvm.data.models.ResponseComic
import com.example.mvvm.data.models.ResponseRegistro
import com.example.mvvm.data.network.InstanceRetrofit
import com.example.mvvm.domain.model.LoginRequest
import com.example.mvvm.domain.model.RegistroRequest
import retrofit2.Response
/*
* Creado por Francisco Nolasco
* Año 2023 | 2024
* */
class ApiServiceClase {
    suspend fun getComic(email: String, password: String): ResponseComic? {
        try {
            val response: Response<ResponseComic> = InstanceRetrofit.retrofitService.login(LoginRequest(email, password))
            if (!response.isSuccessful) {
                showError("Response unsuccessful" + response.body())
            }

            return response.body()
        } catch (e: Exception) {
            showError("Exception: ${e.message}")
            return null
        }
    }

    suspend fun registrarUsuario(email: String, password: String,nombre:String,disponible: String): ResponseRegistro? {

        try {
            val response: Response<ResponseRegistro> = InstanceRetrofit.retrofitService.registro(
                RegistroRequest(email,password,nombre,disponible)
            )
            if (!response.isSuccessful) {
                showError("Response unsuccessful" )
            }

            return response.body()
        } catch (e: Exception) {
            showError("Exception: ${e.message}")
            return null
        }
    }

    private fun showError(message: String) {
        Log.e("TAG", "Error: $message")
        // Aquí podrías mostrar un Toast, un diálogo o cualquier otra acción para informar al usuario sobre el error.
    }
}
