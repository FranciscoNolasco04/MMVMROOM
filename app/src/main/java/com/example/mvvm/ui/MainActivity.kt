package com.example.mvvm.ui


import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mvvm.R
import com.example.mvvm.ui.fragments.FragmentoFavoritos
import com.example.mvvm.ui.fragments.FragmentoLista
import com.example.mvvm.ui.fragments.FragmentoPaginaPrincipal
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.provider.MediaStore
import android.util.Base64
import android.webkit.MimeTypeMap
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.mvvm.data.MyApplication
import com.example.mvvm.data.models.BookRepositoryDao
import com.example.mvvm.data.service.BookService
import com.example.mvvm.ui.BookModel.BookModelView
import com.example.mvvm.ui.logueo.Login
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var framelayout: FrameLayout
    private lateinit var shared : SharedPreferences;
    private  var bookRepositoryDao: BookRepositoryDao = BookRepositoryDao()
    var context = this
    val bookViewModel: BookModelView by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // val username = intent.getStringExtra("username")
        bookRepositoryDao.initData()
        val ficheroPreferencias : String = "preferenciasAppLibros";
        shared = this.getSharedPreferences(ficheroPreferencias, Context.MODE_PRIVATE);

        val username = shared.getString("preferenciasUsername","usuario no encontrado");
        var token = shared.getString("preferenciasToken","NO TOKEN");
        val imagen = shared.getString("preferenciasImagen","NO IMAGEN");
        val id = shared.getString(" preferenciasIdUsuario","NO ID");
        Toast.makeText(this,token,Toast.LENGTH_LONG)




        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavView)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navInicio -> {
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.add(R.id.fragment_container, FragmentoPaginaPrincipal())
                    fragmentTransaction.commit()
                    var titulo = findViewById<TextView>(R.id.custom_title)
                    titulo.text= "Inicio"
                    true
                }
                R.id.navLibros -> {
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.add(R.id.fragment_container, FragmentoLista())
                    fragmentTransaction.commit()
                    var titulo = findViewById<TextView>(R.id.custom_title)
                    titulo.text= "Libros"
                    true
                }
                R.id.navFavoritos -> {
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fragment_container, FragmentoFavoritos())
                    fragmentTransaction.commit()
                    var titulo = findViewById<TextView>(R.id.custom_title)
                    titulo.text= "Favoritos"
                    true
                }
                else -> false
            }
        }
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, FragmentoPaginaPrincipal())
        fragmentTransaction.commit()

        // NAVIGATION DRAWER

        drawer = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val navHeaderTextView = navigationView.getHeaderView(0).findViewById<TextView>(R.id.nav_header_textView)
        navHeaderTextView.text = username
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_item_one -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, FragmentoPaginaPrincipal())
                    .commit()
                var titulo = findViewById<TextView>(R.id.custom_title)
                titulo.text= "Inicio"
            }
            R.id.nav_item_two -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, FragmentoLista())
                    .commit()
                var titulo = findViewById<TextView>(R.id.custom_title)
                titulo.text= "Libros"
            }
            R.id.nav_item_three -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, FragmentoFavoritos())
                    .commit()
                var titulo = findViewById<TextView>(R.id.custom_title)
                titulo.text= "Favoritos"
            }
            R.id.nav_sesion -> {
                val sharedPreferences : SharedPreferences = getSharedPreferences(getString(R.string.preferencias_fichero_login),
                    Context.MODE_PRIVATE);
                val editor : SharedPreferences.Editor = sharedPreferences.edit();
                editor.clear()
                editor.commit()
                finish()
                val intent = Intent(this, Login::class.java)
                //intent.putExtra("username", userFound.username)
                startActivity(intent)
            }
            R.id.nav_perfil -> {
                val toast = Toast.makeText(this, R.string.preferencias_username,Toast.LENGTH_LONG) // in Activity
                toast.show()
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
