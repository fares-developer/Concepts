package com.example.learning.ui.first

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.learning.R
import com.example.learning.data.local.Poster
import com.example.learning.data.local.PosterDatabase
import com.example.learning.databinding.FragmentFirstBinding
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var viewModel: FirstViewModel
    private lateinit var viewModelFac: FirstViewModel.FirstViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("Fragment", "FirstFragment Created")

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_first, container, false)
        binding.lifecycleOwner = viewLifecycleOwner //Esto nos permite eliminar el observer
        val application = requireNotNull(this.activity).application

        val database = PosterDatabase.getInstance(application).posterDatabaseDao
        viewModelFac = FirstViewModel.FirstViewModelFactory(application,database)
        viewModel = ViewModelProvider(this, viewModelFac)[FirstViewModel::class.java]
        binding.firstViewModel = viewModel


        //Initial values assignments
        binding.texto.text = viewModel.textSuccess.value
        binding.countTxt.text = viewModel.count.value.toString()

        viewModel.navigatTo.observe(viewLifecycleOwner) {
            if (it) {
                navigateTo()
            }
        }

        return binding.root
    }

    private fun navigateTo() {
        findNavController().navigate(
            FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                binding.texto.text.toString()
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Fragment", "FirstFragment destroyed")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("Fragment", "FirstFragment destroyed")
    }
}