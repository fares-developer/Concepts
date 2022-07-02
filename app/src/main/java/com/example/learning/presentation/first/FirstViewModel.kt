package com.example.learning.presentation.first

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.learning.data.local.Poster
import com.example.learning.data.local.PosterDatabaseDao
import kotlinx.coroutines.launch

class FirstViewModel(
    application: Application,
    private val database: PosterDatabaseDao

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
        _count.value = 0
        _textSuccess.value = "Hello There!"
        _navigateTo.value = false
        Log.i("ViewModel", "FirstViewModel Created")
        initDb()
    }

    private fun initDb() {
        viewModelScope.launch {
            insertPosters()
        }
    }

    val posters: LiveData<List<Poster>> = database.getAllPosters()


    private suspend fun insertPosters() {
        FirstViewModel.posters.forEach {
            database.insertPoster(it)
        }
    }


    fun changeText(): String {
        _textSuccess.value = "Change success!!!"
        return textSuccess.value.toString()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            FirstViewModel.posters.forEach {
                database.deletePosters(it)
            }
        }
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

    companion object {



        private val posters: List<Poster> = listOf(
            Poster(
                author = "Charles Deluvio", postDownloads = 117559,
                postPath = "https://images.unsplash.com/photo-1517282009859-f000ec3b26fe?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80"
            ),

            Poster(
                author = "Marcus Wallis", postDownloads = 13576,
                postPath = "https://images.unsplash.com/photo-1526865906320-0200a6e2c7f0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=435&q=80"
            ),

            Poster(
                author = "Mae Mu", postDownloads = 111915,
                postPath = "https://images.unsplash.com/photo-1552010099-5dc86fcfaa38?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=464&q=80"
            ),

            Poster(
                author = "Tagerine Newt", postDownloads = 24956,
                postPath = "https://images.unsplash.com/photo-1618897996318-5a901fa6ca71?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=464&q=80"
            ),

            Poster(
                author = "Melisa Belanger", postDownloads = 11628,
                postPath = "https://images.unsplash.com/photo-1594002348772-bc0cb57ade8b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80"
            ),

            Poster(
                author = "Birgith Roosipuu", postDownloads = 7213,
                postPath = "https://images.unsplash.com/photo-1597269391563-7c7837196d69?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80"
            )
        )
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