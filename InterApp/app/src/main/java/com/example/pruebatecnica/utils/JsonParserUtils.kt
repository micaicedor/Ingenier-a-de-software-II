package com.example.pruebatecnica.utils

import com.example.pruebatecnica.data.local.entity.SchemaTableEntity
import com.example.pruebatecnica.data.local.entity.UserEntity
import com.example.pruebatecnica.data.model.Locality
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.util.Locale

object JsonParserUtils {
    fun extractUser(json: JsonElement): UserEntity {
        val usuario = findValueByKey(json, "Usuario") ?: "pam.meredy21"
        val identificacion = findValueByKey(json, "Identificacion") ?: "987204545"
        val nombre = findValueByKey(json, "Nombre") ?: "Nombre no retornado"

        return UserEntity(
            usuario = usuario,
            identificacion = identificacion,
            nombre = nombre
        )
    }

    fun extractVersion(json: JsonElement): String? {
        return findValueByKeyContains(json, listOf("version", "versionapp", "vpstore"))
            ?: findValueByKeyContains(json, listOf("valor", "value"))
            ?: firstPrimitiveValue(json)
    }

    fun extractSchemaTables(json: JsonElement): List<SchemaTableEntity> {
        val objects = mutableListOf<JsonObject>()
        collectObjects(json, objects)

        val tablesFromObjects = objects.mapIndexedNotNull { index, item ->
            val tableName = valueByCandidates(
                item,
                listOf("NombreTabla", "NomTabla", "Tabla", "Table", "TableName", "Name", "Nombre")
            ) ?: valueByKeyContains(item, listOf("tabla", "table"))

            if (tableName.isNullOrBlank()) {
                null
            } else {
                SchemaTableEntity(
                    nombreTabla = tableName,
                    pk = valueByCandidates(item, listOf("Pk", "PK", "PrimaryKey", "LlavePrimaria")) ?: "",
                    batchSize = valueByCandidates(item, listOf("BatchSize", "Batch", "TamanoBatch"))
                        ?.toIntOrNull() ?: 0,
                    fechaActualizacionSincro = valueByCandidates(
                        item,
                        listOf("FechaActualizacionSincro", "FechaActualizacion", "FechaSincro")
                    ) ?: "",
                    descripcion = valueByCandidates(
                        item,
                        listOf("Descripcion", "Description", "Detalle", "Alias")
                    ) ?: "Tabla sincronizada #${index + 1}",
                    rawJson = item.toString()
                )
            }
        }.distinctBy { it.nombreTabla.lowercase(Locale.ROOT) }

        if (tablesFromObjects.isNotEmpty()) return tablesFromObjects

        val primitiveValues = mutableListOf<String>()
        collectPrimitiveValues(json, primitiveValues)

        return primitiveValues
            .filter { value ->
                value.isNotBlank() && (
                    value.contains("tabla", ignoreCase = true) ||
                        value.contains("table", ignoreCase = true) ||
                        value.contains("_")
                    )
            }
            .distinctBy { it.lowercase(Locale.ROOT) }
            .mapIndexed { index, value ->
                SchemaTableEntity(
                    nombreTabla = value.take(120),
                    pk = "",
                    batchSize = 0,
                    fechaActualizacionSincro = "",
                    descripcion = "Registro de esquema sincronizado #${index + 1}",
                    rawJson = value
                )
            }
    }

    fun extractLocalities(json: JsonElement): List<Locality> {
        val objects = mutableListOf<JsonObject>()
        collectObjects(json, objects)

        return objects.mapNotNull { item ->
            val abbreviation = valueByCandidates(
                item,
                listOf("AbreviacionCiudad", "Abreviacion", "CodigoCiudad")
            )
            val fullName = valueByCandidates(
                item,
                listOf("NombreCompleto", "Nombre", "Ciudad")
            )

            if (abbreviation.isNullOrBlank() || fullName.isNullOrBlank()) {
                null
            } else {
                Locality(abbreviation, fullName)
            }
        }
    }

    private fun collectObjects(element: JsonElement?, output: MutableList<JsonObject>) {
        when {
            element == null || element.isJsonNull -> return
            element.isJsonObject -> {
                val obj = element.asJsonObject
                output.add(obj)
                obj.entrySet().forEach { collectObjects(it.value, output) }
            }
            element.isJsonArray -> element.asJsonArray.forEach { collectObjects(it, output) }
        }
    }

    private fun collectPrimitiveValues(element: JsonElement?, output: MutableList<String>) {
        when {
            element == null || element.isJsonNull -> return
            element.isJsonPrimitive -> output.add(element.asString)
            element.isJsonArray -> element.asJsonArray.forEach { collectPrimitiveValues(it, output) }
            element.isJsonObject -> element.asJsonObject.entrySet().forEach {
                collectPrimitiveValues(it.value, output)
            }
        }
    }

    private fun findValueByKey(element: JsonElement?, desiredKey: String): String? {
        return findValueByKeyContains(element, listOf(desiredKey))
    }

    private fun findValueByKeyContains(element: JsonElement?, candidates: List<String>): String? {
        when {
            element == null || element.isJsonNull -> return null
            element.isJsonObject -> {
                element.asJsonObject.entrySet().forEach { entry ->
                    val key = entry.key.lowercase(Locale.ROOT)
                    val matches = candidates.any { key.contains(it.lowercase(Locale.ROOT)) }
                    if (matches && entry.value.isJsonPrimitive) {
                        return entry.value.asString
                    }
                    findValueByKeyContains(entry.value, candidates)?.let { return it }
                }
            }
            element.isJsonArray -> element.asJsonArray.forEach {
                findValueByKeyContains(it, candidates)?.let { value -> return value }
            }
            element.isJsonPrimitive -> return element.asString
        }
        return null
    }

    private fun firstPrimitiveValue(element: JsonElement?): String? {
        when {
            element == null || element.isJsonNull -> return null
            element.isJsonPrimitive -> return element.asString
            element.isJsonArray -> element.asJsonArray.forEach {
                firstPrimitiveValue(it)?.let { value -> return value }
            }
            element.isJsonObject -> element.asJsonObject.entrySet().forEach {
                firstPrimitiveValue(it.value)?.let { value -> return value }
            }
        }
        return null
    }

    private fun valueByCandidates(obj: JsonObject, candidates: List<String>): String? {
        candidates.forEach { candidate ->
            obj.entrySet().firstOrNull {
                it.key.equals(candidate, ignoreCase = true) && it.value.isJsonPrimitive
            }?.let { return it.value.asString }
        }
        return null
    }

    private fun valueByKeyContains(obj: JsonObject, candidates: List<String>): String? {
        return obj.entrySet().firstOrNull { entry ->
            val key = entry.key.lowercase(Locale.ROOT)
            entry.value.isJsonPrimitive && candidates.any { key.contains(it.lowercase(Locale.ROOT)) }
        }?.value?.asString
    }
}
