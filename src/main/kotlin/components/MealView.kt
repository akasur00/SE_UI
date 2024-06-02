package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MealView(mealName: String, ingredients: List<String>, onCountChange: (Int) -> Unit, price: Double) {
  val countMealAdd = remember { mutableStateOf(0) }
  val maxIngredientsPerLine = 4

  Row(
    modifier = Modifier
      .clip(RoundedCornerShape(8.dp))
      .background(color = Color(0xFFEADFDB))
      .padding(16.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Column {
      Text(
        style = MaterialTheme.typography.titleLarge,
        text = "$mealName - Price: $price €"
      )
      Row(
        horizontalArrangement = Arrangement.SpaceBetween,
      ) {
        Column {
          Text(text = "Zutaten: ")
        }
        Column {
          Text(text = ingredients.take(maxIngredientsPerLine).joinToString(separator = ", "))
          if (ingredients.size > maxIngredientsPerLine) {
            Row(
              horizontalArrangement = Arrangement.SpaceBetween,
            ) {
              Text(text = ingredients.drop(maxIngredientsPerLine).joinToString(separator = ", "))
            }
          }
        }
      }
    }
    Spacer(modifier = Modifier.padding(6.dp))
    IconButton(onClick = {
      countMealAdd.value = 1
      onCountChange(countMealAdd.value)
    }) {
      Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }
  }
}

