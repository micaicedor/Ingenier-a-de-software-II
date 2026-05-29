package com.example.pruebatecnica.ui.localities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebatecnica.R
import com.example.pruebatecnica.data.model.Locality

class LocalitiesAdapter : RecyclerView.Adapter<LocalitiesAdapter.LocalityViewHolder>() {
    private val items = mutableListOf<Locality>()

    fun submitList(newItems: List<Locality>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_locality, parent, false)
        return LocalityViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocalityViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class LocalityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtAbbreviation = itemView.findViewById<TextView>(R.id.txtAbbreviation)
        private val txtFullName = itemView.findViewById<TextView>(R.id.txtFullName)

        fun bind(item: Locality) {
            txtAbbreviation.text = item.abreviacionCiudad
            txtFullName.text = item.nombreCompleto
        }
    }
}
