package com.example.mvvm.domain.usercase


import com.example.mvvm.data.models.Book
import com.example.mvvm.data.models.BookRepositoryDao

class DeleteItem(private val repository: BookRepositoryDao, private val pos: Int) {
    operator fun invoke(): List<Book>{
        return repository.deleteItem(pos)
    }
}