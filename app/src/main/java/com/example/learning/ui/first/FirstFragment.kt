package com.example.learning.ui.first

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.learning.R
import com.example.learning.data.local.PosterDatabase
import com.example.learning.databinding.FragmentFirstBinding
import com.example.learning.presentation.first.FirstViewModel
import com.example.learning.ui.first.adapter.PosterAdapter
import com.example.learning.ui.first.adapter.PosterListener

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

        //Esto nos permite eliminar el observer y hace que la vista se actualice automáticamente
        binding.lifecycleOwner = viewLifecycleOwner


        val application = requireNotNull(this.activity).application
        val database = PosterDatabase.getInstance(application).posterDatabaseDao
        viewModelFac = FirstViewModel.FirstViewModelFactory(application,database)
        viewModel = ViewModelProvider(this, viewModelFac)[FirstViewModel::class.java]
        binding.firstViewModel = viewModel

        //Asignación del adapter y carga de los post desde base de datos
        val adapter = PosterAdapter(PosterListener {
            Toast.makeText(this.requireContext(),"Has pulsado la Card ",Toast.LENGTH_SHORT).show()
        })
        binding.postList.adapter = adapter
        viewModel.posters.observe(viewLifecycleOwner) {
            it?.let{
                adapter.submitList(it) //Esto indica a listAdapter que una nueva versión de la lista
            }
        }

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
        viewModel
        Log.i("Fragment", "FirstFragment destroyed")
    }
}