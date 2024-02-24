package com.example.mvvm.ui.logueo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvm.data.models.Username
import com.example.mvvm.data.models.Usernames
import com.example.mvvm.R
import com.example.mvvm.databinding.ActivityLoginBinding
import com.example.mvvm.ui.MainActivity

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val usernames = Usernames.userList
    private lateinit var shared : SharedPreferences;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usernames.add(
            Username("pepe","pepe","pepe","pepe")
        )
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            validateUser()
        }

        binding.textView.setOnClickListener {
            startActivity(Intent(this, Registro::class.java))
            //Animatoo.animateSwipeRight(this)
        }
        cargarPreferenciasCompartidas()
       if(isLogueado()){
           val intent = Intent(this, MainActivity::class.java)
           startActivity(intent)
       }else{
           validateUser()
       }
    }

    fun cerrarSesion(){
        val sharedPreferences : SharedPreferences = getSharedPreferences(getString(R.string.preferencias_fichero_login),Context.MODE_PRIVATE);
        val editor : SharedPreferences.Editor = sharedPreferences.edit();
        editor.clear()
        editor.commit()
        finish()
    }

    private fun cargarPreferenciasCompartidas(){
        val ficheroPreferencias : String = "preferenciasAppLibros";
        shared = this.getSharedPreferences(ficheroPreferencias, Context.MODE_PRIVATE);
    }

    private fun isLogueado() : Boolean{
        val isLogin : Boolean = shared.getBoolean("isLogin",false);
        return isLogin;
    }

    private fun validateUser() {
        val user = binding.edtxtUsuario.text.toString()
        val password = binding.edtxtContrasenna.text.toString()
        val userFound = usernames.find { username ->
            username.username == user && username.password == password
        }

        if (userFound != null) {
            val editor : SharedPreferences.Editor = shared.edit()
            editor.putString("preferenciasUsername",userFound.username);
            editor.putBoolean("isLogin",true);
            editor.commit();

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("username", userFound.username)
            startActivity(intent)

            //Animatoo.animateFade(this)
        } else {
            binding.tilUsuario.error = "Usuario o contraseña incorrecta"
            binding.tilContrasenna.error = "Usuario o contraseña incorrecta"
            binding.edtxtUsuario.setText("")
            binding.edtxtContrasenna.setText("")
        }
    }

}
