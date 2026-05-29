package com.example.pruebatecnica.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.pruebatecnica.BuildConfig
import com.example.pruebatecnica.PruebaTecnicaApp
import com.example.pruebatecnica.R
import com.example.pruebatecnica.ui.ViewModelFactory
import com.example.pruebatecnica.ui.localities.LocalitiesActivity
import com.example.pruebatecnica.ui.tables.TablesActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val app = application as PruebaTecnicaApp
        viewModel = ViewModelProvider(this, ViewModelFactory(app.repository))[HomeViewModel::class.java]

        val txtUsuario = findViewById<TextView>(R.id.txtUsuario)
        val txtIdentificacion = findViewById<TextView>(R.id.txtIdentificacion)
        val txtNombre = findViewById<TextView>(R.id.txtNombre)
        val txtEstado = findViewById<TextView>(R.id.txtEstado)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val btnTablas = findViewById<Button>(R.id.btnTablas)
        val btnLocalidades = findViewById<Button>(R.id.btnLocalidades)

        btnTablas.setOnClickListener {
            startActivity(Intent(this, TablesActivity::class.java))
        }

        btnLocalidades.setOnClickListener {
            startActivity(Intent(this, LocalitiesActivity::class.java))
        }

        viewModel.user.observe(this) { user ->
            txtUsuario.text = "Usuario: ${user?.usuario ?: "Sin datos"}"
            txtIdentificacion.text = "Identificacion: ${user?.identificacion ?: "Sin datos"}"
            txtNombre.text = "Nombre: ${user?.nombre ?: "Sin datos"}"
        }

        viewModel.loading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            btnTablas.isEnabled = !isLoading
            btnLocalidades.isEnabled = !isLoading
        }

        viewModel.status.observe(this) { status ->
            txtEstado.text = status
        }

        viewModel.alert.observe(this) { message ->
            AlertDialog.Builder(this)
                .setTitle("Mensaje")
                .setMessage(message)
                .setPositiveButton("Aceptar", null)
                .show()
        }

        viewModel.initialize(BuildConfig.VERSION_NAME)
    }
}
