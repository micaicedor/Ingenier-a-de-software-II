package com.example.pruebatecnica

import android.app.Application
import com.example.pruebatecnica.data.local.AppDatabase
import com.example.pruebatecnica.network.RetrofitClient
import com.example.pruebatecnica.repository.AppRepository

class PruebaTecnicaApp : Application() {
    val database: AppDatabase by lazy { AppDatabase.getInstance(this) }

    val repository: AppRepository by lazy {
        AppRepository(
            apiService = RetrofitClient.apiService,
            userDao = database.userDao(),
            schemaTableDao = database.schemaTableDao()
        )
    }
}
