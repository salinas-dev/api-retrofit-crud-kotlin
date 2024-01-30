package com.tesji.apptodo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class TareaAdapter(
    var context: Context,
    var listatareas: ArrayList<Tarea>
): RecyclerView.Adapter<TareaAdapter.TareaViewHolder>(){

    private var onClick: OnItemClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_tarea, parent, false)
        return TareaViewHolder(vista)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = listatareas.get(position)

        holder.tvId.text = tarea.id.toString()
        holder.tvTitulo.text = tarea.titulo
        holder.tvDescripcion.text = tarea.descripcion

        holder.btnEditar.setOnClickListener {
            onClick?.editarTarea(tarea)
        }

        holder.btnBorrar.setOnClickListener {
            onClick?.borrarTarea(tarea.id)
        }
    }

    override fun getItemCount(): Int {
        return listatareas.size
    }

    inner class TareaViewHolder(itemView: View): ViewHolder(itemView) {
        val tvId = itemView.findViewById(R.id.tvIdTarea) as TextView
        val tvTitulo = itemView.findViewById(R.id.tvTitulo) as TextView
        val tvDescripcion = itemView.findViewById(R.id.tvDescripcion) as TextView
        val btnEditar = itemView.findViewById(R.id.btnEditar) as Button
        val btnBorrar = itemView.findViewById(R.id.btnBorrar) as Button
    }

    interface OnItemClicked {
        fun editarTarea(tarea: Tarea)
        fun borrarTarea(id: Int)
    }

    fun setOnClick(onClick: OnItemClicked?) {
        this.onClick = onClick
    }
}