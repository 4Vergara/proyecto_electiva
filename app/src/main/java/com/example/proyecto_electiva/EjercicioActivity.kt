package com.example.proyecto_electiva

import AdaptadorEjercicio
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto_electiva.databinding.ActivityEjercicioBinding

data class elementoEjercicio(
    val nombre: String,
    val descripcion: String,
    val imagen: String
)
class EjercicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var binding = ActivityEjercicioBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nombre = intent.getStringExtra("nombre")
        val descripcion = intent.getStringExtra("descripcion")

        binding.tituloLista.text = nombre
        binding.descripcionLista.text = descripcion

        val datos = arrayListOf<elementoEjercicio>()

        datos.add(elementoEjercicio("Flexiones", "Flexiones de brazos", ""))
        datos.add(elementoEjercicio("Sentadillas", "Sentadillas de piernas", ""))
        datos.add(elementoEjercicio("Abdominales", "Abdominales de abdomen", ""))
        datos.add(elementoEjercicio("Plancha", "Plancha de abdomen", ""))

        val adaptadorEjercicio = AdaptadorEjercicio(datos, this)



        binding.vistaEjercicio.adapter = adaptadorEjercicio
    }


}