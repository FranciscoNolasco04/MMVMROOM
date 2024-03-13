package com.example.mvvm.data.service

import android.content.Context
import android.util.MalformedJsonException
import android.widget.Toast
import com.example.mvvm.data.MyApplication
import com.example.mvvm.data.models.Book
import com.example.mvvm.data.models.BookAddRequest
import com.example.mvvm.data.models.BookUpdateResponse
import com.example.mvvm.data.models.Repository
import com.example.mvvm.data.network.InstanceRetrofit
/*
* Creado por Francisco Nolasco
* Año 2023 | 2024
* */
class BookService() {

    suspend fun getBooks(): List<Book>? {
        val sharedPrefs = MyApplication.context.getSharedPreferences("preferenciasAppLibros", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("preferenciasToken", "NO TOKEN")
        val response = InstanceRetrofit.retrofitService.getBooks(token!!)
        return try {
            response.body()?.libros
        } catch (e: MalformedJsonException) {
            e.printStackTrace()
            null
        }
    }

    suspend fun addBook(book: Book) {
        val sharedPrefs = MyApplication.context.getSharedPreferences("preferenciasAppLibros", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("preferenciasToken", "NO TOKEN")

        try {
            Toast.makeText(MyApplication.context, book.toString(), Toast.LENGTH_LONG).show()
            val bookAdd = BookAddRequest(book.id_usuario,book.nombre,book.descripcion,book.imagen)
            val response = InstanceRetrofit.retrofitService.addBook(token!!, bookAdd)
            if (response.isSuccessful) {

            } else {
                Toast.makeText(MyApplication.context, "Error al añadir el libro" + response.message(), Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(MyApplication.context, "Error al añadir el libro", Toast.LENGTH_SHORT).show()
        }
    }


    suspend fun updateBook(book: Book, idBook: String) : BookUpdateResponse? {
        val sharedPrefs = MyApplication.context.getSharedPreferences("preferenciasAppLibros", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("preferenciasToken", "NO TOKEN")
        try {
            val bookUpdate = BookAddRequest(book.id_usuario, book.nombre, book.descripcion, book.imagen)
            val response = InstanceRetrofit.retrofitService.updateBook(token!!, bookUpdate,idBook)
            Toast.makeText(MyApplication.context, "Error al actualizar el libro" + response.message(), Toast.LENGTH_SHORT).show()
            if (response.isSuccessful) {
                return response.body()
            } else {
                Toast.makeText(MyApplication.context, "Error al actualizar el libro" + response.message(), Toast.LENGTH_SHORT).show()
            }

        } catch (e: Exception) {
            Toast.makeText(MyApplication.context, "No se ha podido actualizar el libro", Toast.LENGTH_SHORT).show()
        }
        return null;
    }

    suspend fun deleteBook(idUsuario: String, idLibro: String) {
        val sharedPrefs = MyApplication.context.getSharedPreferences("preferenciasAppLibros", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("preferenciasToken", "NO TOKEN")

        try {
            val response = InstanceRetrofit.retrofitService.deleteBook(token!!, idLibro)
            if (!response.isSuccessful) {
                // Manejar la respuesta si la solicitud no fue exitosa
                // Puedes mostrar un mensaje de error, registrar el error, etc.
            }
        } catch (e: Exception) {
            // Manejar la excepción si ocurre un error durante la solicitud
            // Puedes mostrar un mensaje de error, registrar el error, etc.
        }
    }


}