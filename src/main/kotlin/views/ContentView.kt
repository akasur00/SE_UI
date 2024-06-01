package views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

  val pricePizza = 11.99
  val pricePasta = 3.99

  val fullPrice: Double = (pricePasta*countPizza)+(pricePasta*countPasta)
  val formattedFullPrice = String.format("%.2f", fullPrice)

  Row(
    horizontalArrangement = Arrangement.spacedBy(20.dp)

  ) {
    Column (
      verticalArrangement = Arrangement.spacedBy(20.dp)
    ){
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

    Column (
      verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
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

      if (fullPrice != 0.00 ) {
        Row(
          modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.Green)
            .padding(8.dp)
        ) {
          Text(text = "Price: $formattedFullPrice")
        }
      }
    }
  }
}

