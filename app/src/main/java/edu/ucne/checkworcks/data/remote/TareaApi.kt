package edu.ucne.checkworcks.data.remote

import edu.ucne.checkworcks.data.remote.dto.TareaDto
import retrofit2.Response
import retrofit2.http.*


interface TareaApi{
    @GET("/api/Asignaciones")
    suspend fun getTareas(): List<TareaDto>


    @PUT("api/Asignaciones/{id}")
    suspend fun putTareas(@Path ("id") id: Int,@Body  tareaDto:TareaDto): Response<Unit>


    @GET("/api/Asignaciones/{id}")
    suspend fun getTareasbyId(@Path("id") id: Int): TareaDto

}