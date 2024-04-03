package edu.ucne.checkworcks.Repository

import edu.ucne.checkworcks.data.remote.TareaApi
import edu.ucne.checkworcks.data.remote.dto.TareaDto
import edu.ucne.checkworcks.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException


import javax.inject.Inject

class TareaRepositoryImp @Inject constructor(
    private val api: TareaApi
): TareaRepository {

    override  fun getTareas(): Flow<Resource<List<TareaDto>>> = flow {
        try {
            emit(Resource.Loading())
            val tareas = api.getTareas()

            emit(Resource.Success(tareas))
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: java.io.IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    override suspend fun putTareas(id: Int, tareaDto: TareaDto) {
        api.putTareas(id, tareaDto)
    }


    override  fun getTareasbyId(id: Int): Flow<Resource<TareaDto>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val tareas =
                api.getTareasbyId(id) //descarga las ocupaciones de internet, se supone quedemora algo

            emit(Resource.Success(tareas)) //indicar que se cargo correctamente y pasarle las monedas
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: java.io.IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}