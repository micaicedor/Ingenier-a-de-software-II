package com.example.pruebatecnica.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pruebatecnica.repository.AppRepository
import com.example.pruebatecnica.ui.home.HomeViewModel
import com.example.pruebatecnica.ui.localities.LocalitiesViewModel
import com.example.pruebatecnica.ui.tables.TablesViewModel

class ViewModelFactory(
    private val repository: AppRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository) as T
            modelClass.isAssignableFrom(TablesViewModel::class.java) -> TablesViewModel(repository) as T
            modelClass.isAssignableFrom(LocalitiesViewModel::class.java) -> LocalitiesViewModel(repository) as T
            else -> throw IllegalArgumentException("ViewModel no soportado: ${modelClass.name}")
        }
    }
}
