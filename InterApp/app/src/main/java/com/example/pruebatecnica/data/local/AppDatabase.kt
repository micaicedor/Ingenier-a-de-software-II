package com.example.pruebatecnica.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pruebatecnica.data.local.dao.SchemaTableDao
import com.example.pruebatecnica.data.local.dao.UserDao
import com.example.pruebatecnica.data.local.entity.SchemaTableEntity
import com.example.pruebatecnica.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class, SchemaTableEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun schemaTableDao(): SchemaTableDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "prueba_tecnica.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}
