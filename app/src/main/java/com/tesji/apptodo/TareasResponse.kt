package com.tesji.apptodo

import com.google.gson.annotations.SerializedName

data class TareasResponse(
    @SerializedName("listaTareas") var listaTareas: ArrayList<Tarea>
)


