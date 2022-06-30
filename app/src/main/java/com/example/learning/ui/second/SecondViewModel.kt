package com.example.learning.ui.second

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SecondViewModel:ViewModel() {

    init {
        Log.i("ViewModel","SecondViewModel Created")
    }

    @Suppress("UNCHECKED_CAST")
    class SecondViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SecondViewModel::class.java)) {
                return SecondViewModel() as T
            }

            throw IllegalArgumentException("No se reconoce el Factory")
        }

    }
}