package com.tesji.apptodo

import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

object AppConstantes {
    const val BASE_URL = "http://192.168.43.84:3000"
    //const val BASE_URL = "https://api-restful-nodejs-mysql-production.up.railway.app"
}

interface WebService {

    @GET("/tarea")
    suspend fun obtenerTareas(): Response<TareasResponse>

    @GET("/tarea/{id}")
    suspend fun obtenerTarea(
        @Path("id") id: Int
    ): Response<Tarea>

    @POST("/tarea/add")
    suspend fun agregarTarea(
        @Body tarea: Tarea
    ): Response<String>

    @PUT("/tarea/update/{id}")
    suspend fun actualizarTarea(
        @Path("id") id: Int,
        @Body tarea: Tarea
    ): Response<String>

    @DELETE("/tarea/delete/{id}")
    suspend fun borrarTarea(
        @Path("id") id: Int
    ): Response<String>
}

object RetrofitClient {
    val webService: WebService by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}
