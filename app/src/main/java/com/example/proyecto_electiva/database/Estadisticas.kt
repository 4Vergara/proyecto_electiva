package com.example.proyecto_electiva.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estadisticas")
data class Estadisticas(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val idDeporte: String,
    val nombreDeporte: String,
    val numeroEntradas: Int,
    val createdAt: Long
){
}
