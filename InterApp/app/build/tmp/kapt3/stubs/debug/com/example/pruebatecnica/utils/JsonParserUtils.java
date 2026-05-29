package com.example.pruebatecnica.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J \u0010\n\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u000b0\bH\u0002J\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0006J\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\r2\u0006\u0010\u000f\u001a\u00020\u0006J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0006J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000f\u001a\u00020\u0006J\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0016\u001a\u00020\u000bH\u0002J\"\u0010\u0017\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0002J\u0014\u0010\u0019\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J \u0010\u001a\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001b\u001a\u00020\t2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0002J \u0010\u001c\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001b\u001a\u00020\t2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0002\u00a8\u0006\u001d"}, d2 = {"Lcom/example/pruebatecnica/utils/JsonParserUtils;", "", "()V", "collectObjects", "", "element", "Lcom/google/gson/JsonElement;", "output", "", "Lcom/google/gson/JsonObject;", "collectPrimitiveValues", "", "extractLocalities", "", "Lcom/example/pruebatecnica/data/model/Locality;", "json", "extractSchemaTables", "Lcom/example/pruebatecnica/data/local/entity/SchemaTableEntity;", "extractUser", "Lcom/example/pruebatecnica/data/local/entity/UserEntity;", "extractVersion", "findValueByKey", "desiredKey", "findValueByKeyContains", "candidates", "firstPrimitiveValue", "valueByCandidates", "obj", "valueByKeyContains", "app_debug"})
public final class JsonParserUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.pruebatecnica.utils.JsonParserUtils INSTANCE = null;
    
    private JsonParserUtils() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.pruebatecnica.data.local.entity.UserEntity extractUser(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonElement json) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String extractVersion(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonElement json) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.pruebatecnica.data.local.entity.SchemaTableEntity> extractSchemaTables(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonElement json) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.pruebatecnica.data.model.Locality> extractLocalities(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonElement json) {
        return null;
    }
    
    private final void collectObjects(com.google.gson.JsonElement element, java.util.List<com.google.gson.JsonObject> output) {
    }
    
    private final void collectPrimitiveValues(com.google.gson.JsonElement element, java.util.List<java.lang.String> output) {
    }
    
    private final java.lang.String findValueByKey(com.google.gson.JsonElement element, java.lang.String desiredKey) {
        return null;
    }
    
    private final java.lang.String findValueByKeyContains(com.google.gson.JsonElement element, java.util.List<java.lang.String> candidates) {
        return null;
    }
    
    private final java.lang.String firstPrimitiveValue(com.google.gson.JsonElement element) {
        return null;
    }
    
    private final java.lang.String valueByCandidates(com.google.gson.JsonObject obj, java.util.List<java.lang.String> candidates) {
        return null;
    }
    
    private final java.lang.String valueByKeyContains(com.google.gson.JsonObject obj, java.util.List<java.lang.String> candidates) {
        return null;
    }
}