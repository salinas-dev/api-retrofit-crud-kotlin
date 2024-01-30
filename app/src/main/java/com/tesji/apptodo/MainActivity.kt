package com.tesji.apptodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tesji.apptodo.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), TareaAdapter.OnItemClicked {

    lateinit var binding: ActivityMainBinding
    lateinit var adaptador: TareaAdapter

    var listaTarea = arrayListOf<Tarea>()
    var tarea = Tarea(-1, "","")

    var isEditando = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTarea.layoutManager = LinearLayoutManager(this)
        setupRecyclerView()

        obtenerTareas()

        binding.btnAddUpdate.setOnClickListener {
            var isValido = validarCampos()
            if (isValido) {
                if (!isEditando) {
                    agregarTarea()
                } else {
                    actualizarTarea()
                }
            } else {
                Toast.makeText(this, "Se deben llenar los campos", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun setupRecyclerView() {
        adaptador = TareaAdapter(this, listaTarea)
        adaptador.setOnClick(this@MainActivity)
        binding.rvTarea.adapter = adaptador

    }

    fun validarCampos(): Boolean {
        return !(binding.etTitulo.text.isNullOrEmpty() || binding.etDescripcion.text.isNullOrEmpty())
    }

    fun obtenerTareas() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.obtenerTareas()
            runOnUiThread {
                if (call.isSuccessful) {
                    listaTarea = call.body()!!.listaTareas
                    setupRecyclerView()
                } else {
                    Toast.makeText(this@MainActivity, "ERROR CONSULTAR TODOS", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun agregarTarea() {

        this.tarea.id = -1
        this.tarea.titulo = binding.etTitulo.text.toString()
        this.tarea.descripcion = binding.etDescripcion.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.agregarTarea(tarea)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@MainActivity, call.body().toString(), Toast.LENGTH_LONG).show()
                    obtenerTareas()
                    limpiarCampos()
                    limpiarObjeto()

                } else {
                    Toast.makeText(this@MainActivity, "ERROR ADD", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun actualizarTarea() {

        this.tarea.titulo = binding.etTitulo.text.toString()
        this.tarea.descripcion = binding.etDescripcion.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.actualizarTarea(tarea.id, tarea)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@MainActivity, call.body().toString(), Toast.LENGTH_LONG).show()
                    obtenerTareas()
                    limpiarCampos()
                    limpiarObjeto()

                    binding.btnAddUpdate.setText("Agregar Usuario")
                    binding.btnAddUpdate.backgroundTintList = resources.getColorStateList(R.color.green)
                    isEditando = false
                }
            }
        }
    }

    fun limpiarCampos() {
        binding.etTitulo.setText("")
        binding.etDescripcion.setText("")
    }

    fun limpiarObjeto() {
        this.tarea.id = -1
        this.tarea.titulo = ""
        this.tarea.descripcion = ""
    }

    override fun editarTarea(tarea: Tarea) {
        binding.etTitulo.setText(tarea.titulo)
        binding.etDescripcion.setText(tarea.descripcion)
        binding.btnAddUpdate.setText("Actualizar Tarea")
        binding.btnAddUpdate.backgroundTintList = resources.getColorStateList(R.color.purple_500)
        this.tarea = tarea
        isEditando = true
    }

    override fun borrarTarea(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.borrarTarea(id)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@MainActivity, call.body().toString(), Toast.LENGTH_LONG).show()
                    obtenerTareas()
                }
            }
        }
    }
}