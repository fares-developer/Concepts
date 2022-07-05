package com.example.learning.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.learning.R
import com.example.learning.databinding.FragmentSecondBinding
import com.example.learning.presentation.second.SecondViewModel

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private lateinit var viewModel: SecondViewModel
    private lateinit var viewModelFact: SecondViewModel.SecondViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_second, container, false)
        viewModelFact = SecondViewModel.SecondViewModelFactory()
        viewModel = ViewModelProvider(this,viewModelFact)[SecondViewModel::class.java]

        //binding.texto.text = arguments?.getString("Texto").toString()

        binding.button.setOnClickListener {
            findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToFirstFragment())
        }

        return binding.root
    }
}