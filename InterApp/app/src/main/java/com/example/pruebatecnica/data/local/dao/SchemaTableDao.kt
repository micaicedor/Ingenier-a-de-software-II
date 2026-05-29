package com.example.pruebatecnica.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pruebatecnica.data.local.entity.SchemaTableEntity

@Dao
interface SchemaTableDao {
    @Query("DELETE FROM tablas_sincronizadas")
    suspend fun clearTables()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTables(tables: List<SchemaTableEntity>)

    @Query("SELECT * FROM tablas_sincronizadas ORDER BY nombreTabla ASC")
    suspend fun getTables(): List<SchemaTableEntity>
}
