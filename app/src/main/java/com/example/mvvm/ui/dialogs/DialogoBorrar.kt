package com.example.mvvm.ui.dialogs
// En el DialogoBorrar.kt

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.data.models.BookRepositoryDao
import com.example.mvvm.ui.BookModel.BookModelView

class DialogoBorrar : DialogFragment {

    private lateinit var repositoryDao: BookRepositoryDao
    private lateinit var bookModelView: BookModelView

    constructor() : super() {}

    constructor(repositoryDao: BookRepositoryDao, bookModelView: BookModelView) : super() {
        this.repositoryDao = repositoryDao
        this.bookModelView = bookModelView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())

        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.activity_dialogo_borrar, null)
        view.findViewById<TextView>(R.id.customDialogTitle).text = "Borrar Libro"

        val position = arguments?.getInt("position") ?: -1

        builder.setView(view)
            .setPositiveButton("Aceptar") { dialog, id ->
                if (position != -1) {
                    bookModelView.deleteBook(position,repositoryDao)
                }
            }
            .setNegativeButton("Cancelar") { dialog, id ->
                dialog.cancel()
            }

        return builder.create()
    }
}

