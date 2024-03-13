package com.example.mvvm.ui.BookModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.models.Book
import com.example.mvvm.data.models.BookRepositoryDao
import com.example.mvvm.domain.model.Login
import com.example.mvvm.domain.model.Registro
import com.example.mvvm.domain.usercase.AddItem
import com.example.mvvm.domain.usercase.DeleteItem
import com.example.mvvm.domain.usercase.LoginCaseUse
import com.example.mvvm.domain.usercase.RegistroCaseUse
import com.example.mvvm.domain.usercase.UpdateItem
import kotlinx.coroutines.launch

class BookModelView : ViewModel() {
    var bookListLiveData = MutableLiveData<List<Book>?>()
    var comicLiveData  = MutableLiveData<Login>()
    var registroLiveData  = MutableLiveData<Registro>()
    var progressBarLiveData =  MutableLiveData<Boolean>()
    val useCase = LoginCaseUse()
    val useCaseRegistro = RegistroCaseUse()

    fun addBook(nombre: String, edad: String, imageUrl: String?,repositoryDao1: BookRepositoryDao) {
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

    fun updateBook(position: Int,nombre: String, edad: String, imageUrl: String?,repositoryDao1: BookRepositoryDao,libroID : String) {
        viewModelScope.launch {
            val updateItemCase = UpdateItem(repositoryDao1,position, nombre, edad, imageUrl, libroID)
            bookListLiveData.value =updateItemCase()
        }
    }
    fun searchByComic(email: String, password: String){
        viewModelScope.launch {

            val nuevoLibro = useCase(email, password)
            if (nuevoLibro != null) {
                comicLiveData.value = nuevoLibro!!
            }
        }
    }

    fun registrarUsuario(email: String, password: String,nombre: String, disponible: String) {
        viewModelScope.launch {
            val nuevoComic = useCaseRegistro(email, password, nombre, disponible)
            if (nuevoComic != null) {
                registroLiveData.value = nuevoComic!!
            }
        }
    }

}
