package com.example.pruebatecnica.ui.tables

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebatecnica.R
import com.example.pruebatecnica.data.local.entity.SchemaTableEntity

class TablesAdapter : RecyclerView.Adapter<TablesAdapter.TableViewHolder>() {
    private val items = mutableListOf<SchemaTableEntity>()

    fun submitList(newItems: List<SchemaTableEntity>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_table, parent, false)
        return TableViewHolder(view)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class TableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTableName = itemView.findViewById<TextView>(R.id.txtTableName)
        private val txtTableDescription = itemView.findViewById<TextView>(R.id.txtTableDescription)
        private val txtTableMetadata = itemView.findViewById<TextView>(R.id.txtTableMetadata)

        fun bind(item: SchemaTableEntity) {
            txtTableName.text = item.nombreTabla
            txtTableDescription.text = item.descripcion
            txtTableMetadata.text = "PK: ${item.pk.ifBlank { "N/A" }} | Batch: ${item.batchSize} | Fecha sincro: ${item.fechaActualizacionSincro.ifBlank { "N/A" }}"
        }
    }
}
