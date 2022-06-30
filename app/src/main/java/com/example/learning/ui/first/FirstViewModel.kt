package com.example.learning.ui.first

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.learning.data.local.Poster
import com.example.learning.data.local.PosterDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirstViewModel(
    application: Application,
    val database: PosterDatabaseDao

) : AndroidViewModel(application) {

    //Main Text LiveData
    private var _textSuccess = MutableLiveData<String>()
    val textSuccess: LiveData<String> get() = _textSuccess

    //Count text LiveData
    private var _count = MutableLiveData<Int>()
    val count: LiveData<Int> get() = _count

    //Navigation Action LiveData
    private var _navigateTo = MutableLiveData<Boolean>()
    val navigatTo: LiveData<Boolean> get() = _navigateTo

    init {
        viewModelScope.launch {
            initial()
            insert()

        }
    }

    private suspend fun initial() {
        _count.value = 0
        _textSuccess.value = "Hello There!"
        _navigateTo.value = false
        Log.i("ViewModel", "FirstViewModel Created")
    }

    private fun insert() {
        viewModelScope.launch(Dispatchers.IO) {
            database.insertPosters(
                Poster(author = "Galen Marek", postDownloads = 199),
                Poster(author = "Darth Sidius", postDownloads = 99)
            )
        }
    }


    fun changeText(): String {
        _textSuccess.value = "Change success!!!"
        return textSuccess.value.toString()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ViewModel", "ViewModel destroyed")
    }


    fun countInc() {
        _count.value = _count.value?.plus(1)
    }

    fun countDecr() {
        _count.value?.let {
            if (it > 0) {
                _count.value = _count.value?.minus(1)
            }
        }
    }

    fun navigateTo() {
        _navigateTo.value = true
    }


    @Suppress("UNCHECKED_CAST")
    class FirstViewModelFactory(
        private val application: Application,
        private val database: PosterDatabaseDao
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FirstViewModel::class.java)) {
                return FirstViewModel(application, database) as T
            }

            throw IllegalArgumentException("No se reconoce el Factory")
        }

    }
}

/*
*
* var lista = listOf(
            Poster(
                author = "Charles Deluvio", poster_downloads = 117559,
                poster_path = "@drawable/unsplash1.jpg"
            ),

            Poster(
                author = "Marcus Wallis", poster_downloads = 13576,
                poster_path = "@drawable/unsplash2.jpg"
            ),

            Poster(
                author = "Alexandra Kikot", poster_downloads = 15852,
                poster_path = "@drawable/unsplash3.jpg"
            ),

            Poster(
                author = "Dania Kuleniuk", poster_downloads = 20112,
                poster_path = "@drawable/unsplash4.jpg"
            )
        )
*
* */