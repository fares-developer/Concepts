package com.example.learning.ui.second

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.learning.R
import com.example.learning.data.local.LocalDataSource
import com.example.learning.data.local.PosterDatabase
import com.example.learning.data.model.PosterEntity
import com.example.learning.data.remote.PosterApi
import com.example.learning.data.remote.RemoteDataSource
import com.example.learning.databinding.FragmentSecondBinding
import com.example.learning.presentation.second.SecondViewModel
import com.example.learning.repository.PosterRepositoryImp

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
        binding.lifecycleOwner = viewLifecycleOwner

        //Inyección de dependencias
        val application = requireNotNull(this.activity).application
        val database = PosterDatabase.getInstance(application).posterDatabaseDao

        val repositoryImp = PosterRepositoryImp(
            RemoteDataSource(PosterApi.retrofitService),
            LocalDataSource(database)
        )

        val poster: PosterEntity? = requireArguments().getParcelable("poster")
        viewModelFact = SecondViewModel.SecondViewModelFactory(poster,repositoryImp)
        viewModel = ViewModelProvider(this,viewModelFact)[SecondViewModel::class.java]
        binding.viewModel = viewModel

        requireActivity().window.statusBarColor = Color.parseColor(viewModel.poster.value?.postColorImage)

        binding.button.setOnClickListener {
            findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToFirstFragment())
        }

        return binding.root
    }

}