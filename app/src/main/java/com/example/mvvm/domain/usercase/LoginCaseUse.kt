package com.example.mvvm.domain.usercase

import com.example.mvvm.domain.model.Login
import com.example.mvvm.domain.model.LoginRepository

class LoginCaseUse() {
    private val comicRepository = LoginRepository()

    suspend operator fun invoke(email: String, password: String): Login? = comicRepository.getLogin(email, password)
}
