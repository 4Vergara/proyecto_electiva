package com.example.proyecto_electiva

import AdaptadorEjercicio
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.proyecto_electiva.databinding.ActivityEjercicioBinding
import com.google.firebase.firestore.FirebaseFirestore
import androidx.activity.viewModels
import com.example.proyecto_electiva.ui.EstadisticasViewModel

data class elementoEjercicio(
    val nombre: String,
    val imagen: String,
    val descripcion: String,
    val practica: String,
    val urlVideo: String,
    val idEjercicio: String
)
class EjercicioActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val viewModel: EstadisticasViewModel by viewModels()
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

        val nombreTarjeta = intent.getStringExtra("nombre")
        val descripcionTarjeta = intent.getStringExtra("descripcion")
        val id = intent.getStringExtra("id")
        val imagen = intent.getStringExtra("imagen")


        if (id != null) {
            viewModel.actualizarEntradas(id, nombreTarjeta ?: "")
        }

        val coleccion = when {
            id?.contains("deporte") == true -> "deportes"
            id?.contains("baile") == true -> "bailes"
            id?.contains("arte_marcial") == true -> "artes_marciales"
            else -> null // o una colección predeterminada en caso de que id no coincida con ninguna de las condiciones anteriores
        }

        if (coleccion != null) {
            binding.tituloLista.text = nombreTarjeta
            binding.descripcionLista.text = descripcionTarjeta

            if (!imagen.isNullOrEmpty()) {
                Glide.with(this)
                    .load(imagen)
                    .into(binding.imagen)
            }else{
                binding.imagen.setImageResource(R.drawable.deportes_default)
            }

            val datos = arrayListOf<elementoEjercicio>()

            db.collection(coleccion)
                .whereEqualTo("id", id)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val ejerciciosMap = document.data["ejercicios"] as Map<String, Any>
                        ejerciciosMap.forEach { (idEjercicio, ejercicioData) ->
                            val nombreEjercicio = (ejercicioData as Map<String, Any>)["nombre"].toString()
                            val imagenEjercicio = (ejercicioData as Map<String, Any>)["imagen"].toString()
                            val descripcionEjercicio = (ejercicioData as Map<String, Any>)["descripcion"].toString()
                            val practicaEjercicio = (ejercicioData as Map<String, Any>)["practica"].toString()
                            val urlVideoEjercicio = (ejercicioData as Map<String, Any>)["url_video"].toString()
                            datos.add(elementoEjercicio(nombreEjercicio, imagenEjercicio, descripcionEjercicio, practicaEjercicio, urlVideoEjercicio, idEjercicio))
                        }
                    }


                    val adaptadorEjercicio = AdaptadorEjercicio(datos, this)

                    binding.vistaEjercicio.adapter = adaptadorEjercicio
                }
        }

    }

}