package com.example.pruebatecnica.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class UserEntity(
    @PrimaryKey val id: Int = 1,
    val usuario: String,
    val identificacion: String,
    val nombre: String
)
