package com.example.mvvm.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mvvm.data.dao.UsuarioEntityDao
import com.example.mvvm.data.models.UsuarioEntity

@Database(entities = [UsuarioEntity::class], version = 1, exportSchema = false)
abstract class UsuarioEntityDataBase : RoomDatabase() {
    abstract fun usuarioEntityDao(): UsuarioEntityDao

    companion object {
        @Volatile
        private var INSTANCE: UsuarioEntityDataBase? = null

        fun getDatabase(context: Context): UsuarioEntityDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UsuarioEntityDataBase::class.java,
                    "USUARIOS_DB"
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}