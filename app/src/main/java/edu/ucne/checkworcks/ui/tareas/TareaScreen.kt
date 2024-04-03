package edu.ucne.checkworcks.ui.tareas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.checkworcks.data.remote.dto.TareaDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TareaScreen(
    onNewTicket: () -> Unit,
    viewModel: TareasViewModelApi = hiltViewModel(), onTicketClick: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lista de Tareas",
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            )
        },

        ) {
        val uiState by viewModel.uiState.collectAsState()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            TicketListBody(uiState.tareas) {
                onTicketClick(it)
            }
        }
    }
}

@Composable
fun TicketListBody(tareaList: List<TareaDto>, onTicketClick: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(tareaList) { tarea ->
                TicketRow(tarea) {
                    onTicketClick(it)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketRow(tarea: TareaDto, onTicketClick: (Int) -> Unit) {

    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {


        Column(
            modifier = Modifier
                .clickable(onClick = { onTicketClick(tarea.assigmentId) })
                .fillMaxWidth()
        ) {
            Row() {
                tarea.titulo?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.labelMedium,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.weight(3f)
                    )
                }
                tarea.titulo?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleSmall,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(3f)
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                tarea.comentario?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
                Icon(
                    imageVector = when (tarea.comentario) {
                        "Solicitado" -> {
                            Icons.Default.Star
                        }

                        "En espera" -> {
                            Icons.Default.Update
                        }

                        else -> {
                            Icons.Default.TaskAlt
                        }
                    }, contentDescription = tarea.comentario
                    ,
                    tint = when (tarea.comentario) {
                        "Solicitado" -> {
                            Color(0xFFFFAF32)
                        }

                        "En espera" -> {
                            Color.LightGray
                        }

                        else -> {
                            Color.Green
                        }
                    }
                )
            }
        }
        Divider(Modifier.fillMaxWidth())
    }
}
