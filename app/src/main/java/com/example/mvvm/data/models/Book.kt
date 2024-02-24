package com.example.mvvm.data.models

class Book (
    var name: String,
    var edad: String,
    var image: String
)
{
    override fun toString(): String {
        return "Book(name='$name', creador='$edad', image='$image')"
    }
}