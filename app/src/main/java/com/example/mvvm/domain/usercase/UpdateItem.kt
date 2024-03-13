package com.example.mvvm.domain.usercase

import com.example.mvvm.data.models.Book
import com.example.mvvm.data.models.BookRepositoryDao

class UpdateItem(private val repository: BookRepositoryDao, val posicion : Int, val nombre: String, val edad: String, val imagenUrl: String?,val libroid:String) {
    suspend operator fun invoke():List<Book> {
            return repository.updateItem(posicion,nombre, edad, imagenUrl,libroid)
    }
}