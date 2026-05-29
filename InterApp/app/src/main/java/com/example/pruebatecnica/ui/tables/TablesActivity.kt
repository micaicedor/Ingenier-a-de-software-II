package com.example.pruebatecnica.ui.tables

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebatecnica.PruebaTecnicaApp
import com.example.pruebatecnica.R
import com.example.pruebatecnica.ui.ViewModelFactory

class TablesActivity : AppCompatActivity() {
    private lateinit var viewModel: TablesViewModel
    private val adapter = TablesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables)

        val app = application as PruebaTecnicaApp
        viewModel = ViewModelProvider(this, ViewModelFactory(app.repository))[TablesViewModel::class.java]

        val recyclerTables = findViewById<RecyclerView>(R.id.recyclerTables)
        val txtEmpty = findViewById<TextView>(R.id.txtEmpty)

        recyclerTables.layoutManager = LinearLayoutManager(this)
        recyclerTables.adapter = adapter

        viewModel.tables.observe(this) { tables ->
            adapter.submitList(tables)
            txtEmpty.visibility = if (tables.isEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.loadTables()
    }
}
