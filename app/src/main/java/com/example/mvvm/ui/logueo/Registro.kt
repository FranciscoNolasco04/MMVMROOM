package com.example.mvvm.ui.logueo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.room.ColumnInfo
import com.example.mvvm.data.models.Usernames
import com.example.mvvm.data.models.UsuarioEntity
import com.example.mvvm.data.dao.UsuarioEntityDao
import com.example.mvvm.data.database.UsuarioEntityDataBase
import com.example.mvvm.databinding.ActivityRegistroBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Registro : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding
    private val usuarios = Usernames.userList
    private lateinit var usuarioDao : UsuarioEntityDao;
    private lateinit var db : UsuarioEntityDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = UsuarioEntityDataBase.getDatabase(applicationContext)
        usuarioDao = db.usuarioEntityDao()

        binding.btnCrear.setOnClickListener {
            crearUsuario()
        }
        binding.textView.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            //Animatoo.animateSwipeLeft(this)
        }
    }

    private fun validarCampoNombre(): Boolean {
        val nombre = binding.edtNombre.text.toString().trim()
        if (nombre.isEmpty()) {
            binding.textInputLayoutNombre.error = "Obligatorio*"
            return false
        } else if (!nombre.matches(Regex("[A-Za-z]+"))) {
            binding.textInputLayoutNombre.error = "No es un nombre"
            return false
        } else {
            binding.textInputLayoutNombre.error = null
            return true
        }
    }

    private fun validarCampoApellidos(): Boolean {
        val apellidos = binding.edtApellidos.text.toString().trim()
        if (apellidos.isEmpty()) {
            binding.textInputLayoutApellidos.error = "Obligatorio*"
            return false
        } else if (!apellidos.matches(Regex("[A-Za-z]+"))) {
            binding.textInputLayoutApellidos.error = "No es un apellido"
            return false
        } else {
            binding.textInputLayoutApellidos.error = null
            return true
        }
    }

    private fun validarCampoUsuario(): Boolean {
        val nombreUsuario = binding.edtNombreUsuario.text.toString().trim()
        if (nombreUsuario.isEmpty()) {
            binding.textInputLayoutUsuario.error = "Obligatorio*"
            return false
        } else if (Usernames.isOcupedName(nombreUsuario)) {
            binding.textInputLayoutUsuario.error = "Nombre de usuario ocupado"
            return false
        } else {
            binding.textInputLayoutUsuario.error = null
            return true
        }
    }


    private fun validarCampoContrasenna(): Boolean {
        val contraseña = binding.edtContrasenna.text.toString()
        val repetirContraseña = binding.edtRepeatPassword.text.toString()
        if ( contraseña.isEmpty()){
            binding.textInputLayoutContrasenna.error = "Obligatorio*"
            return false
        }else if (repetirContraseña.isEmpty()){
            binding.textInputLayoutRepetirContrasenna.error = "Obligatorio*"
            return false
        }else if ( contraseña != repetirContraseña){
            binding.textInputLayoutContrasenna.error = "Las contraseñas no coinciden."
            binding.textInputLayoutRepetirContrasenna.error = "Las contraseñas no coinciden."
            return false
        }else{
            binding.textInputLayoutContrasenna.error = null
            binding.textInputLayoutRepetirContrasenna.error = null
            return true
        }
    }

    private fun validarCampos(): Boolean {
        val validateName = validarCampoNombre()
        val validateSecondName = validarCampoApellidos()
        val validateUsername = validarCampoUsuario()
        val validatePassword = validarCampoContrasenna()

        return validateName && validateSecondName && validateUsername && validatePassword
    }


    private fun crearUsuario() {
        if (!validarCampos()) {
            return
        }
        val nombre = binding.edtNombre.text.toString()
        val apellidos =binding.edtApellidos.text.toString()
        val username =binding.edtNombreUsuario.text.toString().trim()
        val password =binding.edtContrasenna.text.toString()

        GlobalScope.launch {
            val nuevoUsuario = UsuarioEntity(name = nombre, secondname = apellidos,username = username, password = password)

            usuarioDao.insertUser(nuevoUsuario)

            startActivity(Intent(this@Registro, Login::class.java))
        }

        //Animatoo.animateSwipeLeft(this)
    }
}
