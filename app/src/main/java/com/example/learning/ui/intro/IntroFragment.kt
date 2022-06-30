package com.example.learning.ui.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.learning.R
import com.example.learning.databinding.FragmentIntroBinding

class IntroFragment : Fragment() {

    private lateinit var binding: FragmentIntroBinding
    private lateinit var viewModel: IntroViewModel
    private lateinit var viewModelFact: IntroViewModel.IntroViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_intro, container, false
        )

        viewModelFact = IntroViewModel.IntroViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFact)[IntroViewModel::class.java]

        viewModel.navigateTo.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(IntroFragmentDirections.actionIntroFragmentToFirstFragment())
            }
        }

        binding.introViewModel = viewModel

        return binding.root
    }


}