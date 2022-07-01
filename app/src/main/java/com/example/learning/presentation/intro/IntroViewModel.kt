package com.example.learning.presentation.intro

import androidx.lifecycle.*

class IntroViewModel : ViewModel() {

    //Navigation LiveData
    private var _navigateTo = MutableLiveData<Boolean>()
    val navigateTo: LiveData<Boolean> get() = _navigateTo

    init {
        _navigateTo.value = false
    }

    fun navigate() {
        _navigateTo.value = true
    }


    @Suppress("UNCHECKED_CAST")
    class IntroViewModelFactory : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(IntroViewModel::class.java)) {
                return IntroViewModel() as T
            }

            throw IllegalArgumentException("No se reconoce el Factory")
        }
    }
}