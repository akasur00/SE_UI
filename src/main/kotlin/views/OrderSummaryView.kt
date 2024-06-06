package views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.ButtonType
import components.CustomButton
import components.MealAreaView

@Composable
fun OrderSummaryView(
  countPizza: Int,
  countPasta: Int,
  fullPrice: String,
  toggleView: () -> Unit
) {
  val horizontalSpaceDP = 20

  var showOrderPlacedMessage by remember { mutableStateOf(false) }
  if (!showOrderPlacedMessage) {

    Column(modifier = Modifier.padding(20.dp))
    {
      Row(horizontalArrangement = Arrangement.spacedBy(horizontalSpaceDP.dp))
      {
        //Bestellung
        MealAreaView("Bestellung:", 16, 8, 0xFFAEAFB1, 320)
        {
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
            text = "Preis: $fullPrice €"
          )
        }

        // Bestellstatus
        MealAreaView("Status:", 16, 8, 0xFFAEAFB1, 320)
        {
          Text(text = "Angenommen", style = MaterialTheme.typography.titleMedium)
          Text(text = "Bearbeitet von: Hannes", style = MaterialTheme.typography.titleSmall)
        }
      }

      Row(
        horizontalArrangement = Arrangement.spacedBy(horizontalSpaceDP.dp)
      ) {
        CustomButton(onButtonClick = {showOrderPlacedMessage = true}, "Bestellung Aufgaben", ButtonType.FORWARD)

        CustomButton(onButtonClick = {toggleView()}, "Bestellung Stornierern", ButtonType.BACKWARD)
      }
    }
  } else {
    Column(modifier = Modifier.padding(20.dp))
    {
      Row(horizontalArrangement = Arrangement.spacedBy(horizontalSpaceDP.dp))
      {
        //Bestellung
        MealAreaView("Bestellung Aufgegeben", 16, 8, 0xFFAEAFB1, 350)
        {}
      }
      Row(horizontalArrangement = Arrangement.spacedBy(horizontalSpaceDP.dp))
      {
        CustomButton(onButtonClick = {toggleView()}, "Neue Bestellung", ButtonType.BACKWARD)
      }
    }
  }
}