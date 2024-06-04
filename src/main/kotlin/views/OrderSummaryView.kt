package views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OrderSummaryView(
  countPizza: Int,
  countPasta: Int,
  fullPrice: String,
  toggleView: () -> Unit
) {
  var showOrderPlacedMessage by remember { mutableStateOf(false) }
  if (!showOrderPlacedMessage) {
    Column {
      Row(
      ) {
        Row(
          modifier = Modifier.padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFAEAFB1))
            .padding(10.dp)
        ) {
          Column {
            Text(text = "Bestellung:", style = MaterialTheme.typography.titleLarge, fontSize = 30.sp)
            Text(
              style = MaterialTheme.typography.titleLarge,
              text = "Pizza: $countPizza"
            )
            Text(
              style = MaterialTheme.typography.titleLarge,
              text = "Pasta: $countPasta"
            )
            Text(
              style = MaterialTheme.typography.titleLarge,
              text = "Preis: $fullPrice"
            )
          }
        }
        Column(
          modifier = Modifier.padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFAEAFB1))
            .padding(10.dp)
        ) {
          Text(text = "Status:", style = MaterialTheme.typography.titleLarge, fontSize = 30.sp)
          Text(text = "Angenommen", style = MaterialTheme.typography.titleMedium)
          Text(text = "Bearbeitet von: Hannes", style = MaterialTheme.typography.titleSmall)
        }
      }

      Row(
        modifier = Modifier.padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
      ) {

        OutlinedButton(onClick = {
          showOrderPlacedMessage = true
        }) {
          Text(text = "Bestellung Aufgaben")
        }
        OutlinedButton(onClick = {
          toggleView()
        }) {
          Text(text = "Bestellung Stornierern")
        }
      }

    }
  } else {
    Row {
      Text(
        modifier = Modifier.fillMaxWidth()
          .padding(top = 50.dp),
        text = "Bestellung aufgegeben",
        style = MaterialTheme.typography.titleLarge,
        fontSize = 30.sp, color = Color.DarkGray,
        textAlign = TextAlign.Center
      )
    }
  }
}