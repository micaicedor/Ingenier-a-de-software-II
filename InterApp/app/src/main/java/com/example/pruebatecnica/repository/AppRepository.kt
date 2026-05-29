package com.example.pruebatecnica.repository

import com.example.pruebatecnica.data.local.dao.SchemaTableDao
import com.example.pruebatecnica.data.local.dao.UserDao
import com.example.pruebatecnica.data.local.entity.SchemaTableEntity
import com.example.pruebatecnica.data.local.entity.UserEntity
import com.example.pruebatecnica.data.model.Locality
import com.example.pruebatecnica.data.model.LoginRequest
import com.example.pruebatecnica.network.ApiResult
import com.example.pruebatecnica.network.ApiService
import com.example.pruebatecnica.utils.JsonParserUtils
import com.example.pruebatecnica.utils.VersionComparator
import java.io.IOException

class AppRepository(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val schemaTableDao: SchemaTableDao
) {
    suspend fun checkVersion(localVersion: String): ApiResult<String> {
        return try {
            val response = apiService.getRemoteVersion()
            if (!response.isSuccessful) {
                return ApiResult.Error("No fue posible consultar la version. HTTP ${response.code()}", response.code())
            }

            val remoteVersion = response.body()?.let(JsonParserUtils::extractVersion)
                ?: return ApiResult.Error("La API no retorno una version valida.", response.code())

            val message = when {
                VersionComparator.compare(localVersion, remoteVersion) < 0 ->
                    "La version local ($localVersion) es inferior a la version de la API ($remoteVersion)."
                VersionComparator.compare(localVersion, remoteVersion) > 0 ->
                    "La version local ($localVersion) es superior a la version de la API ($remoteVersion)."
                else ->
                    "La version local ($localVersion) coincide con la version de la API."
            }

            ApiResult.Success(message)
        } catch (e: IOException) {
            ApiResult.Error("Error de red consultando version: ${e.message}")
        } catch (e: Exception) {
            ApiResult.Error("Error inesperado consultando version: ${e.message}")
        }
    }

    suspend fun login(): ApiResult<UserEntity> {
        return try {
            val response = apiService.login(LoginRequest())
            if (!response.isSuccessful) {
                return ApiResult.Error("Login rechazado por el servidor. HTTP ${response.code()}", response.code())
            }

            val body = response.body()
                ?: return ApiResult.Error("Login exitoso, pero el servidor no retorno datos.", response.code())

            val user = JsonParserUtils.extractUser(body)
            userDao.saveUser(user)
            ApiResult.Success(user)
        } catch (e: IOException) {
            ApiResult.Error("Error de red en login: ${e.message}")
        } catch (e: Exception) {
            ApiResult.Error("Error inesperado en login: ${e.message}")
        }
    }

    suspend fun syncSchema(): ApiResult<Int> {
        return try {
            val response = apiService.getSchema()
            if (!response.isSuccessful) {
                return ApiResult.Error("No fue posible sincronizar tablas. HTTP ${response.code()}", response.code())
            }

            val body = response.body()
                ?: return ApiResult.Error("La API de esquema retorno una respuesta vacia.", response.code())

            val tables = JsonParserUtils.extractSchemaTables(body)
            if (tables.isEmpty()) {
                return ApiResult.Error("La API respondio correctamente, pero no se encontraron tablas en el esquema.")
            }

            schemaTableDao.clearTables()
            schemaTableDao.insertTables(tables)
            ApiResult.Success(tables.size)
        } catch (e: IOException) {
            ApiResult.Error("Error de red sincronizando tablas: ${e.message}")
        } catch (e: Exception) {
            ApiResult.Error("Error inesperado sincronizando tablas: ${e.message}")
        }
    }

    suspend fun getLocalUser(): UserEntity? = userDao.getUser()

    suspend fun getLocalTables(): List<SchemaTableEntity> = schemaTableDao.getTables()

    suspend fun getLocalities(): ApiResult<List<Locality>> {
        return try {
            val response = apiService.getLocalities()
            if (!response.isSuccessful) {
                return ApiResult.Error("No fue posible consultar localidades. HTTP ${response.code()}", response.code())
            }

            val body = response.body()
                ?: return ApiResult.Error("La API de localidades retorno una respuesta vacia.", response.code())

            ApiResult.Success(JsonParserUtils.extractLocalities(body))
        } catch (e: IOException) {
            ApiResult.Error("Error de red consultando localidades: ${e.message}")
        } catch (e: Exception) {
            ApiResult.Error("Error inesperado consultando localidades: ${e.message}")
        }
    }
}
