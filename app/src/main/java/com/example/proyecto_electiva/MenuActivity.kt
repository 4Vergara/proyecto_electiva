package com.example.proyecto_electiva

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto_electiva.databinding.ActivityMenuBinding
import com.google.android.material.tabs.TabLayout
import com.google.firebase.firestore.FirebaseFirestore

data class elemento(
    val nombre: String,
    val descripcion: String,
    val imagen: String,
    val id: String
)

class MenuActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityMenuBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        traerDeportes(binding)

        // Handle tab selection
        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> traerDeportes(binding)
                    1 -> traerBailes(binding)
                    2 -> traerArtes(binding)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselected
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselected
            }
        })



    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.navigation_drawer, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                // Handle profile action
                true
            }
            R.id.action_statistics -> {
                // Handle statistics action
                true
            }
            R.id.action_logout -> {
                // Handle logout action
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun traerDeportes(binding: ActivityMenuBinding){
        val datos  = arrayListOf<elemento>()
        db.collection("deportes").get().addOnSuccessListener { result ->
            for (document in result) {
                val nombre = document.data["nombre"].toString()
                val descripcion = document.data["descripcion"].toString()
                val imagen = document.data["imagen"].toString()
                val id = document.data["id"].toString()
                datos.add(elemento(nombre, descripcion, imagen, id))
            }
            val adaptador = Adaptador(datos, this)

            binding.vistaLista.adapter = adaptador
        }
    }

    private fun traerBailes(binding: ActivityMenuBinding){
        val datos  = arrayListOf<elemento>()
        db.collection("bailes").get().addOnSuccessListener { result ->
            for (document in result) {
                val nombre = document.data["nombre"].toString()
                val descripcion = document.data["descripcion"].toString()
                val imagen = document.data["imagen"].toString()
                val id = document.data["id"].toString()
                datos.add(elemento(nombre, descripcion, imagen, id))
            }
            val adaptador = Adaptador(datos, this)

            binding.vistaLista.adapter = adaptador
        }
    }

    private fun traerArtes(binding: ActivityMenuBinding){
        val datos  = arrayListOf<elemento>()
        db.collection("artes_marciales").get().addOnSuccessListener { result ->
            for (document in result) {
                val nombre = document.data["nombre"].toString()
                val descripcion = document.data["descripcion"].toString()
                val imagen = document.data["imagen"].toString()
                val id = document.data["id"].toString()
                datos.add(elemento(nombre, descripcion, imagen, id))
            }
            val adaptador = Adaptador(datos, this)

            binding.vistaLista.adapter = adaptador
        }
    }

}