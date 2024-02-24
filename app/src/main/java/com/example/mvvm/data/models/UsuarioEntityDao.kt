package com.example.mvvm.data.models

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface UsuarioEntityDao {

    @Query("Select * from usuarios")
    fun getAll() : List<UsuarioEntity>

    @Query("Select * from usuarios where username = :username and password=:password")
    fun getForUsernameAndPassword(username:String , password : String) : UsuarioEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(usuarioEntity: UsuarioEntity)

    @Query("Select * from usuarios where username = :username ")
    fun getForUsernameAndPassword(username:String) : UsuarioEntity?

    //ad
}