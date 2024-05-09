package com.example.proyecto_electiva

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.proyecto_electiva.databinding.ActivityDescripcionEjercicioBinding

class DescripcionEjercicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var binding = ActivityDescripcionEjercicioBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nombre = intent.getStringExtra("nombre")
        val imagen = intent.getStringExtra("imagen")
        val descripcion = intent.getStringExtra("descripcion")
        val practica = intent.getStringExtra("practica")
        val urlVideo = intent.getStringExtra("urlVideo")

        Log.d("Imagen", imagen.toString())

        binding.tituloEjercicio.text = nombre
        binding.descripcionEjercicio.text = descripcion
        binding.practicaEjercicio.text = practica
        binding.urlVideo.text = urlVideo

        if(!imagen.isNullOrEmpty()){
            Glide.with(this)
                .load(imagen)
                .into(binding.imageViewProducto)
        }else{
            binding.imageViewProducto.setImageResource(R.drawable.ejercicios_default)
        }
    }
}