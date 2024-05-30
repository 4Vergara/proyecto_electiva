package com.example.proyecto_electiva.database

import androidx.room.*

@Dao
interface EstadisticasDao {

        @Query("SELECT * FROM estadisticas WHERE idDeporte = :idDeporte LIMIT 1")
        suspend fun getEstadisticasByIdDeporte(idDeporte: String): Estadisticas?

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertEstadisticas(estadisticas: Estadisticas)

        @Update
        suspend fun updateEstadisticas(estadisticas: Estadisticas)

        @Query("SELECT nombreDeporte FROM estadisticas WHERE idDeporte LIKE :idDeporte || '%' ORDER BY numeroEntradas DESC, createdAt DESC LIMIT 1")
        suspend fun getNombreDeporteConMayorNumeroEntradas(idDeporte: String): String?

}