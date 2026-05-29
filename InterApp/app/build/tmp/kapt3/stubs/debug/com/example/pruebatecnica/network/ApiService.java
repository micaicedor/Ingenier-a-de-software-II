package com.example.pruebatecnica.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/example/pruebatecnica/network/ApiService;", "", "getLocalities", "Lretrofit2/Response;", "Lcom/google/gson/JsonElement;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRemoteVersion", "getSchema", "login", "request", "Lcom/example/pruebatecnica/data/model/LoginRequest;", "(Lcom/example/pruebatecnica/data/model/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ApiService {
    
    @retrofit2.http.GET(value = "apicontrollerpruebas/api/ParametrosFramework/ConsultarParametrosFramework/VPStoreAppControl")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRemoteVersion(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.google.gson.JsonElement>> $completion);
    
    @retrofit2.http.Headers(value = {"Usuario: pam.meredy21", "Identificacion: 987204545", "Accept: text/json", "IdUsuario: pam.meredy21", "IdCentroServicio: 1295", "NombreCentroServicio: PTO/BOGOTA/CUND/COL/OF PRINCIPAL - CRA 30 # 7-45", "IdAplicativoOrigen: 9", "Content-Type: application/json"})
    @retrofit2.http.POST(value = "FtEntregaElectronica/MultiCanales/ApiSeguridadPruebas/api/Seguridad/AuthenticaUsuarioApp")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object login(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.pruebatecnica.data.model.LoginRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.google.gson.JsonElement>> $completion);
    
    @retrofit2.http.GET(value = "apicontrollerpruebas/api/SincronizadorDatos/ObtenerEsquema/true")
    @retrofit2.http.Headers(value = {"Usuario: pam.meredy21", "Identificacion: 987204545", "Accept: text/json", "IdUsuario: pam.meredy21", "IdCentroServicio: 1295", "NombreCentroServicio: PTO/BOGOTA/CUND/COL/OF PRINCIPAL - CRA 30 # 7-45", "IdAplicativoOrigen: 9"})
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSchema(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.google.gson.JsonElement>> $completion);
    
    @retrofit2.http.GET(value = "apicontrollerpruebas/api/ParametrosFramework/ObtenerLocalidadesRecogidas")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLocalities(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.google.gson.JsonElement>> $completion);
}