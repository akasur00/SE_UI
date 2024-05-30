package views

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ContentView() {
  Row {
    RowOne()
    RowTwo()
    RowThree()
  }
}

@Composable
fun RowOne() {
  Column(
    modifier = Modifier.padding(5.dp)
  ) {
   Text(text = "hihi")
  }
}

@Composable
fun RowTwo() {
  Column(
    modifier = Modifier.padding(5.dp)
  ) {
    Text(text = "hihi")
  }
}

@Composable
fun RowThree() {
  Column(
    modifier = Modifier.padding(5.dp)
  ) {
    Text(text = "hihi")
  }
}