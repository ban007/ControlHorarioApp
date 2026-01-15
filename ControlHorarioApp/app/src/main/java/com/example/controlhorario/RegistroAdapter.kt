package com.example.controlhorario
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RegistroAdapter(private var registros: List<String>) : RecyclerView.Adapter<RegistroAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textRegistro: TextView = itemView.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textRegistro.text = registros[position]
    }

    override fun getItemCount(): Int = registros.size

    fun actualizarDatos(nuevosRegistros: List<String>) {
        registros = nuevosRegistros
        notifyDataSetChanged()
    }
}
