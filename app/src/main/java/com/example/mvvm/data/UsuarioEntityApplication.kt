package com.example.mvvm.data

import android.app.Application
import androidx.room.Room
import com.example.mvvm.data.database.UsuarioEntityDataBase

class UsuarioEntityApplication : Application() {
    companion object {
        lateinit var database: UsuarioEntityDataBase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            UsuarioEntityDataBase::class.java, "usuarios"
        ).build()
    }
}