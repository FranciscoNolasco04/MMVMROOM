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
import com.example.mvvm.domain.usercase.AddItem
import com.example.mvvm.domain.usercase.UpdateItem
import com.example.mvvm.ui.BookModel.BookModelView
import com.example.mvvm.ui.fragments.FragmentoLista

class DialogoEditar(private val bookModelView: BookModelView, private val position: Int, private val hotel: Book, private val repositoryDao: BookRepositoryDao) : DialogFragment() {



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())

        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.activity_dialogo_annadir, null)
        val editTextNombre = view.findViewById<EditText>(R.id.editTextNombre)
        val editTextEdad = view.findViewById<EditText>(R.id.editTextEdad)
        val editTextImagen = view.findViewById<EditText>(R.id.editTextImagen)
        view.findViewById<TextView>(R.id.customDialogTitle).text = "Editar Libro"
        // Setear valores iniciales
        editTextNombre.setText(hotel.name)
        editTextEdad.setText(hotel.edad)
        editTextImagen.setText(hotel.image)

        builder.setView(view)
            .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, id ->
                val nuevoNombre = editTextNombre.text.toString()
                val nuevaEdad = editTextEdad.text.toString()
                val nuevaImagen = editTextImagen.text.toString()

                //var bookModelView = BookModelView()
                bookModelView.updateBook(position,nuevoNombre,nuevaEdad, nuevaImagen,repositoryDao)
                //repositoryDao.updateItem(position,nuevoNombre,nuevaEdad,nuevaImagen)
                Toast.makeText(context, "Se ha borrado correctamente", Toast.LENGTH_SHORT).show()
            })
            .setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialog, id ->
                // Cierra el diálogo
                dialog.cancel()
            })

        return builder.create()
    }

}
