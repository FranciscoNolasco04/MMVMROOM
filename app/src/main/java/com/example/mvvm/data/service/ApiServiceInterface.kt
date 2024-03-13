package com.example.mvvm.data.service

import com.example.mvvm.data.models.BookAddRequest
import com.example.mvvm.data.models.BookAddResponse
import com.example.mvvm.data.models.BookDeleteResponse
import com.example.mvvm.data.models.BookResponse
import com.example.mvvm.data.models.BookUpdateResponse
import com.example.mvvm.data.models.ResponseComic
import com.example.mvvm.data.models.ResponseRegistro
import com.example.mvvm.domain.model.LoginRequest
import com.example.mvvm.domain.model.RegistroRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query
/*
* Creado por Francisco Nolasco
* Año 2023 | 2024
* */
interface ApiServiceInterface {
    @POST("auth")
    suspend fun login(@Body request: LoginRequest): Response<ResponseComic>

    @POST("registro")
    suspend fun registro(@Body request: RegistroRequest): Response<ResponseRegistro>

    @GET("libro")
    suspend fun getBooks(@Header(value = "api-key")apiKei : String): Response<BookResponse>

    @POST("libro")
    suspend fun addBook(
        @Header(value = "api-key") apiKey: String,
        @Body book: BookAddRequest
    ): Response<BookAddResponse>

    @PUT("libro")
    suspend fun updateBook(
        @Header("api-key") apiKey: String,
        @Body book: BookAddRequest,
        @Query("id") id: String
    ): Response<BookUpdateResponse>

    @DELETE("libro")
    suspend fun deleteBook(
        @Header("api-key") apiKey: String,
        @Query("id") id: String
    ): Response<BookDeleteResponse>
}
