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
import model.WarenkorbItem

@Composable
fun OrderSummaryView(
  warenkorb: List<WarenkorbItem>,
  fullPrice: String,
  toggleView: () -> Unit,
  clearWarenkorb: () -> Unit
) {
  val horizontalSpaceDP = 20
  val columnHeight = 500.dp // Feste Höhe für die Spalten


  var showOrderPlacedMessage by remember { mutableStateOf(false) }
  if (!showOrderPlacedMessage) {

    Column(modifier = Modifier.padding(20.dp))
    {
      Row(
        horizontalArrangement = Arrangement.spacedBy(horizontalSpaceDP.dp),
        modifier = Modifier.height(columnHeight)
      )
      {
        //Bestellung
        MealAreaView("Bestellung:", 16, 8, 0xFFAEAFB1, 320)
        {

          warenkorb.forEach { item ->
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(text = "${item.gericht.name} x${item.anzahl}")
              Text(text = String.format("%.2f €", item.gericht.preis * item.anzahl))
            }
          }

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
      Row(
        horizontalArrangement = Arrangement.spacedBy(horizontalSpaceDP.dp),
        modifier = Modifier.height(columnHeight)
      )
      {
        //Bestellung
        MealAreaView("Bestellung Aufgegeben", 16, 8, 0xFFAEAFB1, 350)
        {}
      }
      Row(horizontalArrangement = Arrangement.spacedBy(horizontalSpaceDP.dp))
      {
        CustomButton(onButtonClick = {
          clearWarenkorb()
          toggleView()
        }, "Neue Bestellung", ButtonType.BACKWARD)
      }
    }
  }
}
