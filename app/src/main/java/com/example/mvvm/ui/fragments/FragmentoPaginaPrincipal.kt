package com.example.mvvm.ui.fragments
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvvm.databinding.FragmentFragmentoPaginaPrincipalBinding

class FragmentoPaginaPrincipal : Fragment() {
    private lateinit var binding : FragmentFragmentoPaginaPrincipalBinding
    private lateinit var shared : SharedPreferences;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentFragmentoPaginaPrincipalBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}