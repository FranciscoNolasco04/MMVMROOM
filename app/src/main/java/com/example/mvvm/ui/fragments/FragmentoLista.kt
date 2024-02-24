package com.example.mvvm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.data.models.Book
import com.example.mvvm.data.models.BookRepositoryDao
import com.example.mvvm.data.models.Repository
import com.example.mvvm.databinding.FragmentFragmentoListaBinding
import com.example.mvvm.ui.BookModel.BookModelView
import com.example.mvvm.ui.MainActivity
import com.example.mvvm.ui.adapter.Adapter
import com.example.mvvm.ui.dialogs.DialogoAnnadir
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class FragmentoLista  /*@Inject constructor() */: Fragment() {
    lateinit var binding: FragmentFragmentoListaBinding
    private lateinit var recyclerView: RecyclerView
    //@Inject
    private var repositoryDao: BookRepositoryDao = BookRepositoryDao()
    private lateinit var adapter: Adapter
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        adapter = Adapter()
        mainActivity = requireActivity() as MainActivity
        binding = FragmentFragmentoListaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            recyclerView = binding.myRecyclerView.findViewById(R.id.myRecyclerView)

            // Inicializa el repositorio DAO

            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = Adapter()
            recyclerView.adapter = adapter
            registerLiveData()

            binding.btnAdd.setOnClickListener {
                // Muestra el diálogo de añadir
                val dialogoAnnadir = DialogoAnnadir(repositoryDao,mainActivity.bookViewModel)
                dialogoAnnadir.show(parentFragmentManager, "DialogoAnnadir")
                //Toast.makeText(context, "Se ha borrado AÑADIR", Toast.LENGTH_SHORT).show()

        }
    }

    private fun registerLiveData() {
        mainActivity.bookViewModel.bookListLiveData.observe(requireActivity(), { myList ->
            binding.myRecyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }


}


