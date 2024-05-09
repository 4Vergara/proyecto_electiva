
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_electiva.DescripcionEjercicioActivity
import com.example.proyecto_electiva.EjercicioActivity
import com.example.proyecto_electiva.R
import com.example.proyecto_electiva.elementoEjercicio

class AdaptadorEjercicio(private val datos: ArrayList<elementoEjercicio>, private val context: Context) : RecyclerView.Adapter<AdaptadorEjercicio.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.titulo_ejercicio)
        val imagen: ImageView = itemView.findViewById(R.id.imagen_ejercicio)
        val descripcion: TextView = itemView.findViewById(R.id.descripcion_ejercicio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_ejercicio, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = datos[position]
        holder.titulo.text = item.nombre
        holder.descripcion.text = item.descripcion

        holder.itemView.setOnClickListener {
            // Crear Intent para iniciar EjercicioActivity
            val intent = Intent(context, DescripcionEjercicioActivity::class.java)

            intent.putExtra("nombre", item.nombre)
            intent.putExtra("descripcion", item.descripcion)
            intent.putExtra("practica", item.practica)
            intent.putExtra("urlVideo", item.urlVideo)


            // Iniciar la actividad
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return datos.size
    }
}

