package com.example.mvvm.domain.usercase

import com.example.mvvm.data.models.Book
import com.example.mvvm.data.models.BookRepositoryDao


class AddItem(private val repository: BookRepositoryDao, val nombre: String, val edad: String, val imagenUrl: String?) {
    operator fun invoke(): List<Book> {
        return repository.addBook(nombre, edad, imagenUrl)
    }
}
