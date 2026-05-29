package com.zelada.jsonplaceholde.domain.model

// Modelo de dominio limpio — sin anotaciones de serialización
data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)

