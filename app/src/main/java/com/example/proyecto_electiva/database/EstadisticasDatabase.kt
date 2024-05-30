package com.example.proyecto_electiva.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Estadisticas::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun estadisticasDao(): EstadisticasDao

}
