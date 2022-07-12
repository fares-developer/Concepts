package com.example.learning.presentation.first

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.learning.data.local.PosterDatabaseDao
import com.example.learning.data.model.Poster
import com.example.learning.data.model.PosterEntity
import com.example.learning.data.remote.PosterApiStatus
import com.example.learning.repository.PosterRepositoryImp
import kotlinx.coroutines.launch

class FirstViewModel(
    application: Application,
    private val repositoryImp: PosterRepositoryImp

) : AndroidViewModel(application) {

    //Petition Status
    private var _status = MutableLiveData<PosterApiStatus>()
    val status: LiveData<PosterApiStatus> get() = _status

    private var _posters = MutableLiveData<List<PosterEntity>>()
    val posters: LiveData<List<PosterEntity>> get() = _posters

    init {
        viewModelScope.launch {
            insertPosters()
            Log.i("ViewModel", "FirstViewModel Created")
        }
    }

    private suspend fun insertPosters() {
        _status.value = PosterApiStatus.LOADING
        try {
            repositoryImp.insertPosters()
            _posters.value = repositoryImp.getAllPosters()
            _status.value = PosterApiStatus.DONE
        } catch (e: Exception) {
            _status.value = PosterApiStatus.ERROR
            Log.e("Retrofit","Error en la petición: "+e.message)
        }
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