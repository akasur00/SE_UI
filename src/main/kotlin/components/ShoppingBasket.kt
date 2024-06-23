package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShoppingBasket(shoppingMealName: String, count: Int, onCountChange: (Int) -> Unit) {
    val countMealRemove = remember { mutableStateOf(0) }
    Row (
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0xFFD8C2B5))
            .width(300.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
        Text(
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp),
            text = "$shoppingMealName $count"
        )
        }
        Column (
        ) {
        IconButton(onClick = {
            countMealRemove.value = 1
            onCountChange(countMealRemove.value)
        }) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
        }
        }
    }
}
