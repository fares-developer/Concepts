package com.example.learning.presentation.second

import androidx.lifecycle.*
import com.example.learning.data.model.PosterEntity
import com.example.learning.repository.PosterRepositoryImp
import kotlinx.coroutines.launch

class SecondViewModel(
    private val posterEntity: PosterEntity,
    private val repositoryImp: PosterRepositoryImp

) : ViewModel() {

    private var _poster = MutableLiveData<PosterEntity>()
    val poster: LiveData<PosterEntity> get() = _poster

    init {
        viewModelScope.launch {
            _poster.value = posterEntity
        }
    }

    @Suppress("UNCHECKED_CAST")
    class SecondViewModelFactory(
        private val posterEntity: PosterEntity?,
        private val repositoryImp: PosterRepositoryImp

    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SecondViewModel::class.java)) {
                return SecondViewModel(posterEntity!!, repositoryImp) as T
            }

            throw IllegalArgumentException("No se reconoce el Factory")
        }

    }
}