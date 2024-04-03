package edu.ucne.checkworcks.Repository

import edu.ucne.checkworcks.data.remote.dto.TareaDto
import edu.ucne.checkworcks.util.Resource
import kotlinx.coroutines.flow.Flow

interface TareaRepository {
    fun getTareas(): Flow<Resource<List<TareaDto>>>

    suspend fun putTareas(id: Int, tareaDto: TareaDto)

    fun getTareasbyId(id: Int): Flow<Resource<TareaDto>>



}