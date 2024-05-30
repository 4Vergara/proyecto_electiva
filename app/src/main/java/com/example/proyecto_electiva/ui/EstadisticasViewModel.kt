package com.example.proyecto_electiva.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.proyecto_electiva.database.AppDatabase
import com.example.proyecto_electiva.database.Estadisticas
import kotlinx.coroutines.launch

class EstadisticasViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(application, AppDatabase::class.java, "app-database").build()
    private val estadisticasDao = db.estadisticasDao()

    fun actualizarEntradas(idDeporte: String, nombreDeporte: String) {
        viewModelScope.launch {
            val estadisticas = estadisticasDao.getEstadisticasByIdDeporte(idDeporte)
            if (estadisticas != null) {
                estadisticasDao.updateEstadisticas(estadisticas.copy(numeroEntradas = estadisticas.numeroEntradas + 1))
            } else {
                estadisticasDao.insertEstadisticas(Estadisticas(idDeporte = idDeporte, nombreDeporte = nombreDeporte, numeroEntradas = 1, createdAt = System.currentTimeMillis()))
            }
        }
    }

    val nombreDeporte = MutableLiveData<String>()
    val nombreBaile = MutableLiveData<String>()
    val nombreArteMarcial = MutableLiveData<String>()

    init {
        actualizarNombresDeporteConMayorNumeroEntradas()
    }

    private fun actualizarNombresDeporteConMayorNumeroEntradas() {
        viewModelScope.launch {
            nombreDeporte.value = estadisticasDao.getNombreDeporteConMayorNumeroEntradas("deporte") ?: "Sin registros"
            nombreBaile.value = estadisticasDao.getNombreDeporteConMayorNumeroEntradas("baile") ?: "Sin registros"
            nombreArteMarcial.value = estadisticasDao.getNombreDeporteConMayorNumeroEntradas("arte_marcial") ?: "Sin registros"
        }
    }

}
