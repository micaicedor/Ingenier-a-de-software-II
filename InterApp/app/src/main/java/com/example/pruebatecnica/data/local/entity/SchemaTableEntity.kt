package com.example.pruebatecnica.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tablas_sincronizadas")
data class SchemaTableEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombreTabla: String,
    val pk: String,
    val batchSize: Int,
    val fechaActualizacionSincro: String,
    val descripcion: String,
    val rawJson: String
)
