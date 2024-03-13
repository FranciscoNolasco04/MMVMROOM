package com.example.mvvm.domain.model

import com.example.mvvm.data.models.ResponseComic
import com.example.mvvm.data.models.ResponseRegistro
import com.example.mvvm.data.service.ApiServiceClase

class LoginRepository {

    private val comicService = ApiServiceClase()

    suspend fun getLogin(email: String, password: String): Login? {
        val response: ResponseComic? = comicService.getComic(email, password)
        return response?.let { Login(it.result, it.token , it.id) }
    }

    suspend fun getRegistro(email: String, password: String,nombre: String, disponible: String): Registro? {
        val response: ResponseRegistro? = comicService.registrarUsuario(email, password, nombre, disponible)
        return response?.let { Registro(it.result, it.insert_id) }
    }
}