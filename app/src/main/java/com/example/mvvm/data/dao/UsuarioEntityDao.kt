package com.example.mvvm.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm.data.models.UsuarioEntity

@Dao
interface UsuarioEntityDao {

    @Query("Select * from usuarios")
    fun getAll() : List<UsuarioEntity>

    @Query("Select * from usuarios where username = :username and password=:password")
    fun getForUsernameAndPassword(username:String , password : String) : UsuarioEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(usuarioEntity: UsuarioEntity)

    @Query("Select * from usuarios where username = :username ")
    fun getForUsername(username:String) : UsuarioEntity?

}