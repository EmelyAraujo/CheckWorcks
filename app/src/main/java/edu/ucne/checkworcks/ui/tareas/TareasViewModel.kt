package edu.ucne.checkworcks.ui.tareas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.checkworcks.Repository.TareaRepositoryImp
import edu.ucne.checkworcks.data.remote.TareaApi
import edu.ucne.checkworcks.data.remote.dto.TareaDto
import edu.ucne.checkworcks.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import javax.inject.Inject

data class TareasListState(
    val isLoading: Boolean = false,
    val tareas: List<TareaDto> = emptyList(),
    val error: String = ""
)
data class TareasState(
    val isLoading: Boolean = false,
    val tarea: TareaDto ? =  null,
    val error: String = ""
)

@HiltViewModel
class TareasViewModelApi @Inject constructor(
    private val tareaRepositoryImp: TareaRepositoryImp,
) : ViewModel() {
    var assigmentId by mutableStateOf(0)
    var titulo by mutableStateOf("")
    var descripcion by mutableStateOf("")
    var fechaLimite by mutableStateOf("")
    var estadoVisualizacion by mutableStateOf(false)
    var estadoIniciada by mutableStateOf(true)
    var estadoProceso by mutableStateOf(false)
    var estadoFinalizada by mutableStateOf(false)
    var progress by mutableStateOf(0)
    var comentario by mutableStateOf("")


     var uiState = MutableStateFlow(TareasListState())
        private set
    var uiStateTarea = MutableStateFlow(TareasState())
        private set

    init {
        load()

    }

    private fun load() {

        tareaRepositoryImp.getTareas().onEach {
            result ->
            when(result){
                is Resource.Loading  ->{
                    uiState.value = TareasListState(isLoading = true)
                }
                is Resource.Success ->{
                    uiState.value = TareasListState(tareas = result.data ?: emptyList())
                }
                is Resource.Error ->{
                    uiState.value = TareasListState(error = result.message ?: "Error desconocido")
                }

            }
        }.launchIn(viewModelScope)
    }



    fun setTareas(id: Int) {
        assigmentId = id
        tareaRepositoryImp.getTareasbyId(assigmentId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateTarea.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateTarea.update {
                        it.copy(tarea = result.data)
                    }
                    assigmentId = assigmentId
                    titulo = uiStateTarea.value.tarea!!.titulo.toString()
                    descripcion = uiStateTarea.value.tarea!!.descripcion.toString()
                    fechaLimite = uiStateTarea.value.tarea!!.fechaLimite.toString()
                    estadoVisualizacion = uiStateTarea.value.tarea!!.estadoVisualizacion
                    estadoIniciada = uiStateTarea.value.tarea!!.estadoIniciada
                    estadoProceso = uiStateTarea.value.tarea!!.estadoProceso
                    estadoFinalizada = uiStateTarea.value.tarea!!.estadoFinalizada
                    progress = uiStateTarea.value.tarea!!.progress
                    comentario = uiStateTarea.value.tarea!!.comentario.toString()

                }
                is Resource.Error -> {
                    uiStateTarea.update {
                        it.copy(
                            error = result.message ?: "Error desconocido"
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun putTicket(id : Int) {
        assigmentId = id
        viewModelScope.launch {
            tareaRepositoryImp.putTareas(
                assigmentId, TareaDto(
                    assigmentId,
                    titulo,
                    descripcion,
                    fechaLimite,
                    estadoVisualizacion,
                    uiStateTarea.value.tarea!!.estadoIniciada,
                    uiStateTarea.value.tarea!!.estadoProceso,
                    uiStateTarea.value.tarea!!.estadoFinalizada,
                    uiStateTarea.value.tarea!!.progress,
                    uiStateTarea.value.tarea!!.comentario,
                )
            )
        }

    }


}