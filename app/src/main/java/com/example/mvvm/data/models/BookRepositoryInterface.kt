package com.example.mvvm.data.models

/*
* Creado por Francisco Nolasco
* Año 2023 | 2024
* */

interface BookRepositoryInterface {


    fun initData()


    fun deleteItem(pos: Int): MutableList<Book>
    suspend fun updateItem(pos: Int, nombre: String, edad: String, imagenUrl: String?,libroID : String): MutableList<Book>

    fun addBook(nombre: String, edad: String, imagenUrl: String?): MutableList<Book>
}