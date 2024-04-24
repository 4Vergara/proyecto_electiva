package com.example.proyecto_electiva

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto_electiva.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityMenuBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        var datos  = arrayListOf(
            "Lista deportes 1",
            "Lista deportes 2",
            "Lista deportes 3"
        )

        var adaptador = Adaptador(datos)

        binding.vistaLista.adapter = adaptador
    }
}