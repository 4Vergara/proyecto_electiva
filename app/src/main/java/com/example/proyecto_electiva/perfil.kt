package com.example.proyecto_electiva

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_electiva.databinding.ActivityPerfilBinding
import com.example.proyecto_electiva.ui.EstadisticasViewModel

class perfil : AppCompatActivity() {
    private lateinit var viewModel: EstadisticasViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Corrige la inflación de la vista usando View Binding
        var binding = ActivityPerfilBinding.inflate(layoutInflater, null, false)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this).get(EstadisticasViewModel::class.java)

        viewModel.nombreDeporte.observe(this, Observer { nombreDeporte ->
            Log.d("Perfil", "Nombre deporte: $nombreDeporte")
            binding.sportNameTextView.text = nombreDeporte
        })

        viewModel.nombreBaile.observe(this, Observer { nombreBaile ->
            Log.d("Perfil", "Nombre baile: $nombreBaile")
            binding.danceNameTextView.text = nombreBaile
        })

        viewModel.nombreArteMarcial.observe(this, Observer { nombreArteMarcial ->
            Log.d("Perfil", "Nombre arte marcial: $nombreArteMarcial")
            binding.martialArtsNameTextView.text = nombreArteMarcial
        })
    }
}



