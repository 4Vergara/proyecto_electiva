package com.example.proyecto_electiva

import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
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

        //Cargar el video de youtube por un webView

        val webView: WebView = findViewById(R.id.urlVideo)
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()

        val videoHtml = if (urlVideo != null && urlVideo.isNotEmpty()) {
            val embedUrl = convertToEmbedUrl(urlVideo)
            "<iframe width=\"100%\" height=\"100%\" src=\"$embedUrl\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"
        } else {
            "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/8sjn-bJOBBQ?si=G4AtrJsm_bM7zxHD\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"
        }

        webView.loadDataWithBaseURL(null, videoHtml, "text/html", "utf-8", null)

        webView.settings.javaScriptEnabled = true

        webView.webChromeClient = WebChromeClient()

        binding.tituloEjercicio.text = nombre
        binding.descripcionEjercicio.text = descripcion
        binding.practicaEjercicio.text = practica


        if(!imagen.isNullOrEmpty()){
            Glide.with(this)
                .load(imagen)
                .into(binding.imageViewProducto)
        }else{
            binding.imageViewProducto.setImageResource(R.drawable.ejercicios_default)
        }
    }

    private fun convertToEmbedUrl(url: String): String {
        return if (url.contains("watch?v=")) {
            url.replace("watch?v=", "embed/")
        } else {
            url
        }
    }
}