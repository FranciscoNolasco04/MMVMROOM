package com.example.mvvm.domain.usercase

import com.example.mvvm.domain.model.LoginRepository
import com.example.mvvm.domain.model.Registro

class RegistroCaseUse() {
    private val comicRepository = LoginRepository()

    suspend operator fun invoke(
        email: String,
        password: String,
        nombre: String,
        disponible: String
    ): Registro? = comicRepository.getRegistro(email, password, nombre, disponible)
}