package com.example.learning.presentation.first

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.learning.data.local.PosterDatabaseDao
import com.example.learning.data.model.Poster
import com.example.learning.data.remote.PosterApiStatus
import com.example.learning.repository.PosterRepositoryImp
import kotlinx.coroutines.launch

class FirstViewModel(
    application: Application,
    private val repositoryImp: PosterRepositoryImp

) : AndroidViewModel(application) {

    //Navigation Action LiveData
    private var _navigateTo = MutableLiveData<Boolean>()
    val navigatTo: LiveData<Boolean> get() = _navigateTo

    //Petition Status
    private var _status = MutableLiveData<PosterApiStatus>()
    val status: LiveData<PosterApiStatus> get() = _status

    //Poster Status for charge each card item
    private var _statusCard = MutableLiveData<PosterApiStatus>()
    val statusCard: LiveData<PosterApiStatus> get() = _statusCard



    private var _posters = MutableLiveData<List<Poster>>()
    val posters: LiveData<List<Poster>> get() = _posters

    init {
        viewModelScope.launch {
            _navigateTo.value = false
            insertPosters()
            Log.i("ViewModel", "FirstViewModel Created")
        }
    }

    private suspend fun insertPosters() {
        _status.value = PosterApiStatus.LOADING
        try {
            _posters.value = repositoryImp.insertPosters()
            _status.value = PosterApiStatus.DONE
        } catch (e: Exception) {
            _status.value = PosterApiStatus.ERROR
            Log.e("Retrofit","Error en la petición: "+e.message)
        }
    }


    fun navigateTo() {
        _navigateTo.value = true
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ViewModel", "ViewModel destroyed")
    }

    @Suppress("UNCHECKED_CAST")
    class FirstViewModelFactory(
        private val application: Application,
        private val repositoryImp: PosterRepositoryImp
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FirstViewModel::class.java)) {
                return FirstViewModel(application,repositoryImp ) as T
            }

            throw IllegalArgumentException("No se reconoce el Factory")
        }
    }
}