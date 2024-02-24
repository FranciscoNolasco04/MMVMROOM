package com.example.mvvm.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.data.models.Book
import com.example.mvvm.data.models.BookRepositoryDao
import com.example.mvvm.data.models.Repository
import com.example.mvvm.domain.usercase.AddItem
import com.example.mvvm.ui.BookModel.BookModelView

class DialogoAnnadir(private val repositoryDao: BookRepositoryDao,private val bookViewModel: BookModelView) : DialogFragment() {



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())

        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.activity_dialogo_annadir, null)
        view.findViewById<TextView>(R.id.customDialogTitle).text = "Nuevo Libro"

        builder.setView(view)
            .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, id ->
                val nombreEditText = view.findViewById<EditText>(R.id.editTextNombre)
                val edadEditText = view.findViewById<EditText>(R.id.editTextEdad)
                val imageUrlEditText = view.findViewById<EditText>(R.id.editTextImagen)

                val nombre = nombreEditText.text.toString()
                val edad = edadEditText.text.toString()
                val imageUrl = imageUrlEditText.text.toString()

                // Añade el libro utilizando el ViewModel
                bookViewModel.addBook(nombre, edad, imageUrl,repositoryDao)

                dialog.dismiss()
            })
            .setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialog, id ->
                // Cierra el diálogo
                dialog.cancel()
            })

        return builder.create()
    }
}

