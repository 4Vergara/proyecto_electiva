package com.example.proyecto_electiva

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Adaptador(private val dataSet: ArrayList<elemento>) :
    RecyclerView.Adapter<Adaptador.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.tituloLista)
        val descripcion: TextView = view.findViewById(R.id.descripcionLista)
        val imagen: ImageView = view.findViewById(R.id.imagen)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.lista_menu, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val elemento = dataSet[position]
        holder.titulo.text = elemento.nombre
        holder.descripcion.text = elemento.descripcion
    }
}
