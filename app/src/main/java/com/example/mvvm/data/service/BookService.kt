package com.example.mvvm.data.service

import com.example.mvvm.data.datasource.Books
import com.example.mvvm.data.models.Book

class BookService {
    fun getBooks(): List<Book> {
        return Books.listHotels
    }
}