package com.example.pruebatecnica.network

import com.example.pruebatecnica.data.model.LoginRequest
import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @GET("apicontrollerpruebas/api/ParametrosFramework/ConsultarParametrosFramework/VPStoreAppControl")
    suspend fun getRemoteVersion(): Response<JsonElement>

    @Headers(
        "Usuario: pam.meredy21",
        "Identificacion: 987204545",
        "Accept: text/json",
        "IdUsuario: pam.meredy21",
        "IdCentroServicio: 1295",
        "NombreCentroServicio: PTO/BOGOTA/CUND/COL/OF PRINCIPAL - CRA 30 # 7-45",
        "IdAplicativoOrigen: 9",
        "Content-Type: application/json"
    )
    @POST("FtEntregaElectronica/MultiCanales/ApiSeguridadPruebas/api/Seguridad/AuthenticaUsuarioApp")
    suspend fun login(@Body request: LoginRequest): Response<JsonElement>

    @GET("apicontrollerpruebas/api/SincronizadorDatos/ObtenerEsquema/true")
    @Headers(
        "Usuario: pam.meredy21",
        "Identificacion: 987204545",
        "Accept: text/json",
        "IdUsuario: pam.meredy21",
        "IdCentroServicio: 1295",
        "NombreCentroServicio: PTO/BOGOTA/CUND/COL/OF PRINCIPAL - CRA 30 # 7-45",
        "IdAplicativoOrigen: 9"
    )
    suspend fun getSchema(): Response<JsonElement>

    @GET("apicontrollerpruebas/api/ParametrosFramework/ObtenerLocalidadesRecogidas")
    suspend fun getLocalities(): Response<JsonElement>
}
