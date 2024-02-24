package com.example.mvvm.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName =  "usuarios")
data class UsuarioEntity (
    @PrimaryKey val id : Int=1,
    @ColumnInfo(name = "username") val username : String,
    @ColumnInfo(name = "password") val password : String
)