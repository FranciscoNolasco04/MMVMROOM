package com.example.mvvm.data.models

/*
* Creado por Francisco Nolasco
* Año 2023 | 2024
* */

class Usernames(){
    companion object {
        val userList = mutableListOf<Username>()
        fun isOcupedName(username: String): Boolean {
            for (user in userList) {
                if (user.username == username) {
                    return true
                }
            }
            return false
        }
    }
}
