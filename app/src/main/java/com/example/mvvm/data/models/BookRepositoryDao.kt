package com.example.mvvm.data.models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.data.models.Book
import com.example.mvvm.data.service.BookService
import com.example.mvvm.ui.adapter.Adapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

class BookRepositoryDao /*@Inject constructor() */: BookRepositoryInterface{

    private val booksLiveData = MutableLiveData<List<Book>>()

    override fun initData() {
        val dataSource = BookService().getBooks()
        Repository.books = dataSource.toMutableList()
        booksLiveData.value = Repository.books
    }


    override fun deleteItem(pos: Int): MutableList<Book> {
        if (pos in 0 until Repository.books.size) {
            Repository.books.removeAt(pos)
        }
        return Repository.books
    }

    override fun updateItem(pos: Int, nombre: String, edad: String, imagenUrl: String): MutableList<Book> {
        val updatedBook = Book(nombre, edad, imagenUrl)
        Repository.books[pos] = updatedBook
        return Repository.books
    }

    override fun addBook(nombre: String, edad: String, imagenUrl: String): MutableList<Book> {
        val newBook = Book(nombre, edad, imagenUrl)
        Repository.books.add(newBook)
        return Repository.books
    }

}

