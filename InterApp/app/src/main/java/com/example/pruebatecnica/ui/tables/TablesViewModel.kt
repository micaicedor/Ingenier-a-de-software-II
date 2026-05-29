package com.example.pruebatecnica.ui.tables

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnica.data.local.entity.SchemaTableEntity
import com.example.pruebatecnica.repository.AppRepository
import kotlinx.coroutines.launch

class TablesViewModel(
    private val repository: AppRepository
) : ViewModel() {
    private val _tables = MutableLiveData<List<SchemaTableEntity>>(emptyList())
    val tables: LiveData<List<SchemaTableEntity>> = _tables

    fun loadTables() {
        viewModelScope.launch {
            _tables.value = repository.getLocalTables()
        }
    }
}
