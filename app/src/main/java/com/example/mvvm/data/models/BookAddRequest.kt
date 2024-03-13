package com.example.mvvm.data.models
/*
* Creado por Francisco Nolasco
* Año 2023 | 2024
* */
data class BookAddRequest(
    val id_usuario: String,
    val nombre: String,
    val descripcion: String,
    val imagen: String?
)