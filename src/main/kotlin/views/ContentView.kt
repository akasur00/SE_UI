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
import androidx.compose.ui.unit.dp
import components.MealView
import components.ShoppingBasket

@Composable
fun ContentView() {
  var countPizza by remember { mutableStateOf(0) }
  var countPasta by remember { mutableStateOf(0) }
  var showOrderSummary by remember { mutableStateOf(false) }

  val pricePizza = 11.99
  val pricePasta = 3.99

  val fullPrice: Double = (pricePasta*countPizza)+(pricePasta*countPasta)
  val formattedFullPrice = String.format("%.2f", fullPrice)

  if (!showOrderSummary) {
    Column(
      modifier = Modifier.padding(20.dp)
    ) {
      Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
      ) {
        Column(
          verticalArrangement = Arrangement.spacedBy(20.dp),
          modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0xFFAEAFB1))
            .padding(16.dp)
            .width(300.dp)
        ) {
          Text(
            style = MaterialTheme.typography.headlineLarge,
            text = "Gerichte"
          )
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

        Column(
          verticalArrangement = Arrangement.spacedBy(20.dp),
          modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0xFFAEAFB1))
            .width(200.dp)
            .padding(16.dp)
        ) {
          Text(
            style = MaterialTheme.typography.headlineLarge,
            text = "Warenkorb"
          )
          if (countPizza != 0) {
            Row {
              ShoppingBasket(
                "Pizza", count = countPizza,
                onCountChange = { newCount ->
                  countPizza -= newCount
                }
              )
            }
          }
          if (countPasta != 0) {
            Row {
              ShoppingBasket(
                "Pasta", count = countPasta,
                onCountChange = { newCount ->
                  countPasta -= newCount
                }
              )
            }
          }


          Row(
            modifier = Modifier
              .clip(RoundedCornerShape(8.dp))

              .padding(8.dp)
          ) {
            Text(
              style = MaterialTheme.typography.titleLarge,
              text = "Preis: $formattedFullPrice"
            )
          }
        }
      }
      Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
      ) {
        OutlinedButton(
          onClick = {
            showOrderSummary = !showOrderSummary
          }) {
          Text(text = "Bestellen")
        }
        OutlinedButton(
          onClick = {
            countPasta = 0
            countPizza = 0
          }) {
          Text(text = "Stornieren")
        }
      }
    }
  } else {
    OrderSummaryView(countPizza, countPasta, formattedFullPrice) { showOrderSummary = showOrderSummary.not() }
  }
}

