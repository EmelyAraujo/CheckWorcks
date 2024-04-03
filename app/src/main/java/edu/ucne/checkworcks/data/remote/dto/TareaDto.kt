package edu.ucne.checkworcks.data.remote.dto

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class TareaDto(

    val assigmentId: Int,
    val titulo: String?,
    val descripcion: String?,
    val fechaLimite: String,
    val estadoVisualizacion: Boolean,
    val estadoIniciada: Boolean,
    val estadoProceso: Boolean,
    val estadoFinalizada: Boolean,
    val progress: Int,
    val comentario: String?


)
