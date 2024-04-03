package edu.ucne.checkworcks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.checkworcks.ui.navigation.NavigationGraph
import edu.ucne.checkworcks.ui.theme.CheckWorcksTheme
import edu.ucne.checkworcks.ui.tareas.TareaScreen




@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CheckWorcksTheme {
                NavigationGraph()
            }
        }
    }
}
