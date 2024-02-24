package com.example.mvvm.ui.BookModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.models.Book
import com.example.mvvm.data.models.BookRepositoryDao
import com.example.mvvm.domain.usercase.AddItem
import com.example.mvvm.domain.usercase.DeleteItem
import com.example.mvvm.domain.usercase.UpdateItem
import kotlinx.coroutines.launch

class BookModelView : ViewModel() {
    var bookListLiveData = MutableLiveData<List<Book>>()


    fun addBook(nombre: String, edad: String, imageUrl: String,repositoryDao1: BookRepositoryDao) {
        viewModelScope.launch {
            val addItemUseCase = AddItem(repositoryDao1, nombre, edad, imageUrl)

            bookListLiveData.value =addItemUseCase()
        }
    }
    fun deleteBook(position: Int, repositoryDao1: BookRepositoryDao) {
        viewModelScope.launch {
            val deleteItemUseCase = DeleteItem(repositoryDao1, position)
            bookListLiveData.value =deleteItemUseCase()
        }
    }
    fun updateBook(position: Int,nombre: String, edad: String, imageUrl: String,repositoryDao1: BookRepositoryDao) {
        viewModelScope.launch {
            val updateItemCase = UpdateItem(repositoryDao1,position, nombre, edad, imageUrl)
            bookListLiveData.value =updateItemCase()
        }
    }

}
