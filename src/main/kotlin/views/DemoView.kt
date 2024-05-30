package views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import components.MealView

@Composable
fun DemoView() {
  MealView("Pizza", listOf("Tomaten", "Mehl", "Käse"))
}

@Composable
fun CustomText(text: String) {
  Text(
    style = MaterialTheme.typography.labelLarge,
    text = text
  )
}