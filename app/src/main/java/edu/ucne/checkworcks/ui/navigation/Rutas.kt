package edu.ucne.checkworcks.ui.navigation

sealed class Rutas(var ruta: String){
    object Home: Rutas( "home")
    object Tareas: Rutas("tareas")
    object  TicketC: Rutas("tickets_list")
}
