package com.example.pruebatecnica.ui.localities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnica.data.model.Locality
import com.example.pruebatecnica.network.ApiResult
import com.example.pruebatecnica.repository.AppRepository
import kotlinx.coroutines.launch

class LocalitiesViewModel(
    private val repository: AppRepository
) : ViewModel() {
    private val _localities = MutableLiveData<List<Locality>>(emptyList())
    val localities: LiveData<List<Locality>> = _localities

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadLocalities() {
        viewModelScope.launch {
            _loading.value = true
            when (val result = repository.getLocalities()) {
                is ApiResult.Success -> _localities.value = result.data
                is ApiResult.Error -> _error.value = result.message
            }
            _loading.value = false
        }
    }
}
