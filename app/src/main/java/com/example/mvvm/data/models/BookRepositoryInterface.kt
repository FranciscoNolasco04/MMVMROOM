package com.example.mvvm.data.models

import com.example.mvvm.data.service.BookService

interface BookRepositoryInterface {

    fun initData()


    fun deleteItem(pos: Int): MutableList<Book>
    fun updateItem(pos: Int, nombre: String, edad: String, imagenUrl: String): MutableList<Book>

    fun addBook(nombre: String, edad: String, imagenUrl: String): MutableList<Book>
}