package com.example.mvvm.data.models


import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.mvvm.data.MyApplication
import com.example.mvvm.data.service.BookService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
/*
* Creado por Francisco Nolasco
* Año 2023 | 2024
* */

class BookRepositoryDao() : BookRepositoryInterface{

    val booksLiveData = MutableLiveData<List<Book>>()
    private val bookService = BookService()

    override fun initData() {
        GlobalScope.launch {
            val dataSource = bookService.getBooks()
            Repository.books = dataSource?.toMutableList() ?: mutableListOf()

        }
        booksLiveData.value = Repository.books
    }


    override fun deleteItem(pos: Int): MutableList<Book> {
       /* if (pos in 0 until Repository.books.size) {
            Repository.books.removeAt(pos)
        }*/
        val sharedPrefs = MyApplication.context.getSharedPreferences("preferenciasAppLibros", Context.MODE_PRIVATE)
        val id = sharedPrefs.getString("preferenciasIdUsuario", "NO id")
        val idBook = Repository.books[pos].id

        GlobalScope.launch(Dispatchers.Main)  {
            try {

                bookService.deleteBook(id!!, idBook)


            } catch (e: Exception) {
                Toast.makeText(MyApplication.context, "No se ha podido eliminar el libro", Toast.LENGTH_SHORT).show()
            }
        }
        //Repository.books.removeAt(pos)
        var lista = Repository.books
        lista.removeAt(pos)
        booksLiveData.value = lista
        Repository.books = lista

        return Repository.books
    }

    override suspend fun updateItem(pos: Int, nombre: String, descripcion: String, imagenUrl: String?,libroID : String): MutableList<Book> {
        val sharedPrefs = MyApplication.context.getSharedPreferences("preferenciasAppLibros", Context.MODE_PRIVATE)
        val id = sharedPrefs.getString("preferenciasIdUsuario", "NO id")
        var idBook = Repository.books[pos].id
        var updatedBook = Book(libroID,id!!,nombre, descripcion , imagenUrl)
        // Repository.books[pos] = updatedBook
        try {
            var response = bookService.updateBook(updatedBook,idBook)
            if (response?.result == "ok actualizacion"){
                var lista = Repository.books
                updatedBook.imagen = response.file_img
                lista.set(pos,updatedBook)
                booksLiveData.value = lista
                Repository.books = lista
            }
        }catch (e: Exception){
            Toast.makeText(MyApplication.context,"No se ha podido añadir",Toast.LENGTH_SHORT)
        }


        return Repository.books
    }

    override fun addBook(nombre: String, edad: String, imagenUrl: String?): MutableList<Book> {
        val sharedPrefs = MyApplication.context.getSharedPreferences("preferenciasAppLibros", Context.MODE_PRIVATE)
        val id = sharedPrefs.getString("preferenciasIdUsuario", "NO id")
        val newBook = Book(id!!,nombre, edad, imagenUrl)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                bookService.addBook(newBook)

                val dataSource = bookService.getBooks()
                Repository.books = dataSource?.toMutableList() ?: mutableListOf()
                booksLiveData.value = Repository.books
                booksLiveData.postValue(Repository.books)
            }catch (e: Exception){
                Toast.makeText(MyApplication.context,"No se ha podido añadir",Toast.LENGTH_SHORT)
            }

        }

        return Repository.books
    }

}

