package edu.ucne.checkworcks.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.checkworcks.ui.tareas.TareaScreen


@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Rutas.TicketC.ruta
    )
    {
        /*composable(route = Rutas.Home.ruta) {
            HomeScreen(navController)
        }*/
        composable(route = Rutas.TicketC.ruta) {
            TareaScreen(onNewTicket = {}) { //id ->
               // navController.navigate(Rutas.TicketS.ruta + "/${id}")
            }
        }
       /* composable(
            route = Rutas.TicketS.ruta + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { capturar ->
            val ticketId = capturar.arguments?.getInt("id") ?: 0
            TicketScreen(ticketId = ticketId) {
                navController.navigate(Rutas.TicketC.ruta)
            }
        }*/
    }
}