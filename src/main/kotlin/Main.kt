import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import views.ContentView

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import model.Gericht

@Composable
@Preview
fun App() {
  var gerichte by remember { mutableStateOf<List<Gericht>>(emptyList()) }
  val coroutineScope = rememberCoroutineScope()

  LaunchedEffect(Unit) {
    coroutineScope.launch {
      gerichte = network()
    }
  }

  MaterialTheme {
    ContentView(gerichte)
  }
}

suspend fun network(): List<Gericht> {
  val client = HttpClient(CIO)
  val response: HttpResponse = client.get("http://localhost:8080/gerichte")
  val jsonString = response.bodyAsText()
  client.close()

  return Json.decodeFromString<List<Gericht>>(jsonString)
}

fun main() = application {
  Window(
    onCloseRequest = ::exitApplication,
    state = WindowState(width = 940.dp, height = 650.dp)
  ) {
    App()
  }
}