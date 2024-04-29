package com.example.proyecto_electiva

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto_electiva.databinding.ActivityMenuBinding
import com.google.firebase.firestore.FirebaseFirestore

data class elemento(
    val nombre: String,
    val descripcion: String,
    val imagen: String
)

class MenuActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityMenuBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)
        val datos  = arrayListOf<elemento>()
        db.collection("deportes").get().addOnSuccessListener { result ->
            for (document in result) {
                val nombre = document.data["nombre"].toString()
                val descripcion = document.data["descripcion"].toString()
                val imagen = document.data["imagen"].toString()
                datos.add(elemento(nombre, descripcion, imagen))
            }
            val adaptador = Adaptador(datos)

            binding.vistaLista.adapter = adaptador
        }

    }
}