package com.example.mvvm.data.models

class Repository {
    companion object {
        var books:MutableList<Book> = emptyList<Book>().toMutableList()

    }
}