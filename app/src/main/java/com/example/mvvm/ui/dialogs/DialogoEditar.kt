package com.example.mvvm.ui.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.webkit.MimeTypeMap
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.mvvm.R
import com.example.mvvm.data.MyApplication
import com.example.mvvm.data.models.Book
import com.example.mvvm.data.models.BookRepositoryDao
import com.example.mvvm.ui.BookModel.BookModelView
import kotlinx.coroutines.delay
import java.io.ByteArrayOutputStream


class DialogoEditar(private val bookModelView: BookModelView, private val position: Int, private val hotel: Book, private val repositoryDao: BookRepositoryDao) : DialogFragment() {

    private lateinit var imageView: ImageView
    private var base64: String? = null;

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())

        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.activity_dialogo_annadir, null)
        val editTextNombre = view.findViewById<EditText>(R.id.editTextNombre)
        val editTextEdad = view.findViewById<EditText>(R.id.editTextEdad)

        view.findViewById<TextView>(R.id.customDialogTitle).text = "Editar Libro"
        editTextNombre.setText(hotel.nombre)
        editTextEdad.setText(hotel.descripcion)
        imageView = view.findViewById(R.id.imageView)

        val btnCamara = view.findViewById<ImageButton>(R.id.buttonSeleccionarImagen)
        val btnGaleria = view.findViewById<ImageButton>(R.id.buttonSeleccionarImagenGaleria)
        btnCamara.setOnClickListener {
            startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
        btnGaleria.setOnClickListener {
            pickPhotoFromGallery()
        }

        Glide.with(this)
            .load(hotel.imagen)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.error_image)
            .into(imageView)

        builder.setView(view)
            .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, id ->
                val nuevoNombre = editTextNombre.text.toString()
                val nuevaEdad = editTextEdad.text.toString()
                val nuevaImagen = base64

                bookModelView.updateBook(position,nuevoNombre,nuevaEdad, nuevaImagen,repositoryDao,hotel.id)

            })
            .setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })

        return builder.create()
    }
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val imageBitmap = intent?.extras?.get("data") as Bitmap
                imageView.setImageBitmap(imageBitmap)

                val base64Image = bitmapToBase64(imageBitmap,"")
                base64 = base64Image
            }
        }


    private fun bitmapToBase64(bitmap: Bitmap,type:String): String {

        var tipo = ""
        if (type == ""){
            tipo = "PNG"
        }else{
            tipo = type
        }

        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        val base64Image = Base64.encodeToString(byteArray, Base64.DEFAULT)
        return "data:image/$tipo;base64,$base64Image"
    }

    private val startForActivityGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data?.data
            val  bitmap:Bitmap = MediaStore.Images.Media.getBitmap(MyApplication.context.getContentResolver(), data);

            val cR = MyApplication.context.getContentResolver();
            val mime =  MimeTypeMap.getSingleton();
            val type = mime.getExtensionFromMimeType(cR.getType(data!!));
            type.toString()
            val base64Image = bitmapToBase64(bitmap,  type.toString())
            imageView.setImageURI(data)
            base64 = base64Image
        }
    }
    private fun pickPhotoFromGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startForActivityGallery.launch(intent)
    }



}
