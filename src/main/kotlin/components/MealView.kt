package components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MealView(mealName: String, ingredients: List<String>) {
  Row {
    Column {
      Text(
        style = MaterialTheme.typography.titleLarge,
        text = mealName
      )
      Row(
        horizontalArrangement = Arrangement.SpaceBetween,
      ) {
        ingredients.forEach { ingredient ->
          Text(
            modifier = Modifier.padding(10.dp),
            text = ingredient
          )
        }
      }
    }
    IconButton(onClick = {}) {
      Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }
  }
}