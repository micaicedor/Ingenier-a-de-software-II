package com.example.pruebatecnica.ui.localities

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebatecnica.PruebaTecnicaApp
import com.example.pruebatecnica.R
import com.example.pruebatecnica.ui.ViewModelFactory

class LocalitiesActivity : AppCompatActivity() {
    private lateinit var viewModel: LocalitiesViewModel
    private val adapter = LocalitiesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_localities)

        val app = application as PruebaTecnicaApp
        viewModel = ViewModelProvider(this, ViewModelFactory(app.repository))[LocalitiesViewModel::class.java]

        val recyclerLocalities = findViewById<RecyclerView>(R.id.recyclerLocalities)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val txtEmpty = findViewById<TextView>(R.id.txtEmpty)

        recyclerLocalities.layoutManager = LinearLayoutManager(this)
        recyclerLocalities.adapter = adapter

        viewModel.localities.observe(this) { localities ->
            adapter.submitList(localities)
            txtEmpty.visibility = if (localities.isEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.loading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) { message ->
            AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("Aceptar", null)
                .show()
        }

        viewModel.loadLocalities()
    }
}
