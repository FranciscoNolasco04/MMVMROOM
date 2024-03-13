package com.example.mvvm.data.models
/*
* Creado por Francisco Nolasco
* Año 2023 | 2024
* */
data class Book(
    val id: String,
    val id_usuario: String,
    val nombre: String,
    val descripcion: String,
    var imagen: String?
){
    constructor(id_usuario: String, nombre: String, descripcion: String, imagen: String?) :
            this("0", id_usuario, nombre, descripcion, imagen)
}
