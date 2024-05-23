package com.example.proyecto_electiva

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto_electiva.databinding.ActivityMainBinding
import com.example.proyecto_electiva.databinding.ActivityMenuBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        binding.buttonSing.setOnClickListener {
            var menu = Intent(this,MenuActivity::class.java)
            startActivity(menu)
        }
    }
}