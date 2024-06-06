package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MealAreaView(areaText: String, spaceBetweenMealsDP: Int, cornerRoundDP: Int, backgroundColorCode: Long,
                 areaWidthDP: Int, content: @Composable () -> Unit)
{
  Column(
    verticalArrangement = Arrangement.spacedBy(spaceBetweenMealsDP.dp),
    modifier = Modifier
      .clip(RoundedCornerShape(cornerRoundDP.dp))
      .background(color = Color(backgroundColorCode))
      .padding(spaceBetweenMealsDP.dp)
      .width(areaWidthDP.dp)
  ) {
    Text(
      style = MaterialTheme.typography.headlineLarge,
      text = areaText
    )

    content()

  }

}