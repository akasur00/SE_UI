import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import views.ContentView

@Composable
@Preview
fun App() {
  MaterialTheme {
    ContentView()
  }
}

fun main() = application {
  Window(onCloseRequest = ::exitApplication) {
    App()
  }
}
