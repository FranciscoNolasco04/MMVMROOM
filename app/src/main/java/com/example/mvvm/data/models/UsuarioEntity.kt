package com.example.mvvm.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/*
* Creado por Francisco Nolasco
* Año 2023 | 2024
* */
@Entity(tableName =  "usuarios")
data class UsuarioEntity (
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "secondname") val secondname : String,
    @ColumnInfo(name = "username") val username : String,
    @ColumnInfo(name = "password") val password : String
)
