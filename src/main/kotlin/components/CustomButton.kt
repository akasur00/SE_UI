package components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.FilledTonalButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton (onButtonClick: () -> Unit, buttonText: String, buttonType: ButtonType) {
  val buttonPadding = 5

  if (buttonType == ButtonType.FORWARD) {
    Button(onClick = {
      onButtonClick() },
      modifier = Modifier.padding(buttonPadding.dp)
    )
    {
      Text(buttonText)
    }
  }
  if (buttonType == ButtonType.BACKWARD) {
    FilledTonalButton(onClick = { onButtonClick() },
      modifier = Modifier.padding(buttonPadding.dp)
    )
    {
      Text(buttonText)
    }
  }
}