package views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.*

@Composable
fun ContentView() {
  val horizontalSpaceDP = 20

  var countPizza by remember { mutableStateOf(0) }
  var countPasta by remember { mutableStateOf(0) }
  var showOrderSummary by remember { mutableStateOf(false) }

  val pricePizza = 11.99
  val pricePasta = 3.99

  val fullPrice: Double = (pricePasta*countPizza)+(pricePasta*countPasta)
  val formattedFullPrice = String.format("%.2f", fullPrice)

  if (!showOrderSummary) {

    Column(modifier = Modifier.padding(20.dp))
    {
      Row(horizontalArrangement = Arrangement.spacedBy(horizontalSpaceDP.dp))
      {
        // Gerichte
        MealAreaView("Gerichte", 16, 8, 0xFFAEAFB1, 320)
        {
          // PIZZA
          MealView(
            "Pizza",
            listOf("Tomaten", "Mehl", "Hefe", "Salz", "Wasser", "Olivenöl", "Käse"),
            onCountChange = { newCount -> countPizza += newCount },
            pricePizza
          )

          // PASTA
          MealView(
            "Pasta",
            listOf("Tomaten", "Mehl", "Käse"),
            onCountChange = { newCount -> countPasta += newCount },
            pricePasta
          )
        }

        // Warenkorb
        MealAreaView("Warenkorb", 16, 8, 0xFFAEAFB1, 320)
        {
          if (countPizza != 0) {
            Row {
              ShoppingBasket(
                "Pizza", count = countPizza,
                onCountChange = { newCount -> countPizza -= newCount }
              )
            }
          }
          if (countPasta != 0) {
            Row {
              ShoppingBasket(
                "Pasta", count = countPasta,
                onCountChange = { newCount -> countPasta -= newCount }
              )
            }
          }
          Row {
            Text(
              style = MaterialTheme.typography.titleLarge,
              text = "Preis: $formattedFullPrice €"
            )
          }
        }
      }

      // Buttons
      Row(horizontalArrangement = Arrangement.spacedBy(horizontalSpaceDP.dp))
      {
        CustomButton(onButtonClick = {showOrderSummary = !showOrderSummary}, "Bestellung starten", ButtonType.FORWARD)

        CustomButton(onButtonClick = {countPasta = 0; countPizza = 0}, "Warenkorb löschen", ButtonType.BACKWARD)
      }
    }
  } else {
    // OrderSummary-Fenster öffnen
    OrderSummaryView(
      countPizza,
      countPasta,
      formattedFullPrice,
      toggleView = {
        showOrderSummary = !showOrderSummary
        // Bestellung zurücksetzen
        if (!showOrderSummary) {
          countPizza = 0
          countPasta = 0
        }
      })

  }
}

