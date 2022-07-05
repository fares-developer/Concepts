package com.example.learning.presentation.first

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.learning.data.model.Poster
import com.example.learning.data.local.PosterDatabaseDao
import com.example.learning.data.remote.PosterApi
import com.example.learning.utils.apiKey
import com.example.learning.utils.orientationReq
import com.example.learning.utils.queryReq
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstViewModel(
    application: Application,
    private val database: PosterDatabaseDao

) : AndroidViewModel(application), LifecycleEventObserver {

    val posters: LiveData<List<Poster>> = database.getAllPosters()

    //Main Text LiveData
    private var _textSuccess = MutableLiveData<String>()
    val textSuccess: LiveData<String> get() = _textSuccess

    //Count text LiveData
    private var _count = MutableLiveData<Int>()
    val count: LiveData<Int> get() = _count

    //Navigation Action LiveData
    private var _navigateTo = MutableLiveData<Boolean>()
    val navigatTo: LiveData<Boolean> get() = _navigateTo

    private var _response = MutableLiveData<String>()
    val resp:LiveData<String> get() = _response

    private fun getPostersStateProperties() {
        _response.value = "Conexión exitosa"

        PosterApi.retrofitService.getProperties(queryReq, orientationReq, apiKey).enqueue(
            object :Callback<String>{

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.i("Retrofit",_response.value.toString())
                    _response.value = response.body()
                    Log.i("Retrofit",_response.value.toString())
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.e("Retrofit",t.message.toString())
                }

            }
        )
    }

    private val postersList: List<Poster> = listOf(
        Poster(
            id = 0L,
            author = "Charles Deluvio", postDownloads = 117559,
            postPath = "https://images.unsplash.com/photo-1517282009859-f000ec3b26fe?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80"
        ),

        Poster(
            id = 1L,
            author = "Marcus Wallis", postDownloads = 13576,
            postPath = "https://images.unsplash.com/photo-1526865906320-0200a6e2c7f0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=435&q=80"
        ),

        Poster(
            id = 2L,
            author = "Mae Mu", postDownloads = 111915,
            postPath = "https://images.unsplash.com/photo-1552010099-5dc86fcfaa38?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=464&q=80"
        ),

        Poster(
            id = 3L,
            author = "Tagerine Newt", postDownloads = 24956,
            postPath = "https://images.unsplash.com/photo-1618897996318-5a901fa6ca71?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=464&q=80"
        ),

        Poster(
            id = 4L,
            author = "Melisa Belanger", postDownloads = 11628,
            postPath = "https://images.unsplash.com/photo-1594002348772-bc0cb57ade8b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80"
        ),

        Poster(
            id = 5L,
            author = "Birgith Roosipuu", postDownloads = 7213,
            postPath = "https://images.unsplash.com/photo-1597269391563-7c7837196d69?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80"
        )
    )

    init {
        viewModelScope.launch {
            _count.value = 0
            _textSuccess.value = "Hello There!"
            _navigateTo.value = false
            insertPosters()
            Log.i("ViewModel", "FirstViewModel Created")
            getPostersStateProperties()
        }
    }

    private suspend fun insertPosters() {
        postersList.forEach {
            database.insertPoster(it)
        }
    }


    //En esta app este método se encarga de insertar en la BD cuando se crea el viewModel
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_CREATE) {
            viewModelScope.launch {
                postersList.forEach {
                    database.insertPoster(it)
                }
            }
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