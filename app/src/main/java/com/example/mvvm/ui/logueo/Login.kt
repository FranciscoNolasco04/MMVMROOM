package com.example.mvvm.ui.logueo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.mvvm.data.models.Usernames
import com.example.mvvm.R
import com.example.mvvm.data.dao.UsuarioEntityDao
import com.example.mvvm.data.database.UsuarioEntityDataBase
import com.example.mvvm.databinding.ActivityLoginBinding
import com.example.mvvm.ui.BookModel.BookModelView
import com.example.mvvm.ui.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val usernames = Usernames.userList
    private lateinit var shared : SharedPreferences;
    private lateinit var usuarioDao : UsuarioEntityDao;
    private lateinit var db : UsuarioEntityDataBase
    private val viewModel: BookModelView by viewModels()
    private var result = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = UsuarioEntityDataBase.getDatabase(applicationContext)
        usuarioDao = db.usuarioEntityDao()

        registerLiveData()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progressBar.isVisible = false
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
       }
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
        GlobalScope.launch(Dispatchers.Main) {

            viewModel.searchByComic(user, password)
            delay(2000)
            val usuario = viewModel.comicLiveData.value
            val id = usuario!!.id
            val token = usuario!!.token
            if (result == "ok") {

                    val editor : SharedPreferences.Editor = shared.edit()
                    editor.putString("preferenciasUsername", user)
                    editor.putString("preferenciasIdUsuario", id)
                    editor.putString("preferenciasToken", token)
                    editor.putBoolean("isLogin",true)
                    editor.commit()

                    val intent = Intent(this@Login, MainActivity::class.java)
                    //intent.putExtra("username", user)
                    startActivity(intent)
                    //Toast.makeText(this@Login, token, Toast.LENGTH_LONG).show()
                  //  Toast.makeText(this@Login, id, Toast.LENGTH_LONG).show()


            } else {
                   binding.tilUsuario.error = "Usuario o contraseña incorrecta"
                   binding.tilContrasenna.error = "Usuario o contraseña incorrecta"
                   binding.edtxtUsuario.setText("")
                   binding.edtxtContrasenna.setText("")
            }
        }
    }
    private fun registerLiveData() {
        viewModel.comicLiveData.observe(
            this, {
                    myComic-> result = myComic.result
            }
        )

        viewModel.progressBarLiveData.observe(
            this, {
                    visible-> binding.progressBar.isVisible = false
            }
        )
    }

}
