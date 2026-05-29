package com.example.pruebatecnica.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnica.data.local.entity.UserEntity
import com.example.pruebatecnica.network.ApiResult
import com.example.pruebatecnica.repository.AppRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: AppRepository
) : ViewModel() {
    private val _user = MutableLiveData<UserEntity?>()
    val user: LiveData<UserEntity?> = _user

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _status = MutableLiveData("Preparando aplicacion...")
    val status: LiveData<String> = _status

    private val _alert = MutableLiveData<String>()
    val alert: LiveData<String> = _alert

    fun initialize(localVersion: String) {
        viewModelScope.launch {
            _loading.value = true

            _status.value = "Consultando version del aplicativo..."
            when (val versionResult = repository.checkVersion(localVersion)) {
                is ApiResult.Success -> _alert.value = versionResult.data
                is ApiResult.Error -> _alert.value = versionResult.message
            }

            _status.value = "Autenticando usuario..."
            when (val loginResult = repository.login()) {
                is ApiResult.Success -> _user.value = loginResult.data
                is ApiResult.Error -> {
                    _alert.value = loginResult.message
                    _user.value = repository.getLocalUser()
                }
            }

            _status.value = "Sincronizando tablas..."
            when (val schemaResult = repository.syncSchema()) {
                is ApiResult.Success -> _status.value = "Tablas sincronizadas: ${schemaResult.data}"
                is ApiResult.Error -> {
                    _status.value = "Sincronizacion incompleta"
                    _alert.value = schemaResult.message
                }
            }

            _loading.value = false
        }
    }
}
