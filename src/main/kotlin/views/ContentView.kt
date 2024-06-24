package views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.BestellPosition
import model.Bestellung
import model.Gericht
import model.WarenkorbItem
import java.time.LocalDateTime

@Composable
fun ContentView(gerichte: List<Gericht>) {
  val horizontalSpaceDP = 20
  val columnHeight = 500.dp // Feste Höhe für die Spalten

  var showOrderSummary by remember { mutableStateOf(false) }
  var warenkorb by remember { mutableStateOf(listOf<WarenkorbItem>()) }
  val coroutineScope = rememberCoroutineScope()

  val fullPrice = warenkorb.sumOf { it.gericht.preis * it.anzahl }
  val formattedFullPrice = String.format("%.2f", fullPrice)

  if (!showOrderSummary) {

    Column(
      modifier = Modifier
        .padding(20.dp))
    {
      Row(
        horizontalArrangement = Arrangement.spacedBy(horizontalSpaceDP.dp),
        modifier = Modifier.height(columnHeight)
      )
      {
        // Gerichte
        MealAreaView("Gerichte", 16, 8, 0xFFAEAFB1, 520)
        {

          gerichte.forEach { gericht ->
            val zutatenNamen = gericht.zutatenListe.map { it.name }
            // PIZZA
            MealView(
              gericht.name,
              zutatenNamen,
              onCountChange = { newCount ->
                warenkorb = updateWarenkorb(warenkorb, gericht, newCount)
              },
              String.format("%.2f", gericht.preis)
            )
          }
        }

        // Warenkorb
        MealAreaView("Warenkorb", 16, 8, 0xFFAEAFB1, 340)
        {
          warenkorb.forEach { item ->
            Row {
              ShoppingBasket(
                item.gericht.name,
                count = item.anzahl,
                onCountChange = { newCount ->
                  warenkorb = updateWarenkorb(warenkorb, item.gericht, -newCount)
                }
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
        CustomButton(
          onButtonClick = {
            coroutineScope.launch {
              val bestellPositionen = warenkorb.map { item ->
                BestellPosition(
                  bestellPositionId = 0, // oder eine andere ID, falls erforderlich
                  gerichtID = item.gericht.id,
                  preis = item.gericht.preis
                )
              }
              val bestellung = Bestellung(
                id = 0, // oder eine andere ID, falls erforderlich
                kundenPosition = null, // oder eine tatsächliche KundenPosition
                mitarbeiterPosition = null, // oder eine tatsächliche MitarbeiterPosition
                entscheidungBestellung = null, // oder eine tatsächliche EntscheidungBestellung
                bestellPositionen = bestellPositionen,
                erstellDatum = LocalDateTime.now().toString()
              )
              val response = sendBestellung(bestellung)
              if (response.status == HttpStatusCode.OK) {
                showOrderSummary = !showOrderSummary
              } else {
                // Fehlerbehandlung
                println("Fehler bei der Übermittlung der Bestellung: ${response.status}")
              }
            }
          },
          "Bestellung starten",
          ButtonType.FORWARD
        )

        CustomButton(onButtonClick = {warenkorb = emptyList() }, "Warenkorb löschen", ButtonType.BACKWARD)
      }
    }
  } else {
    // OrderSummary-Fenster öffnen
    OrderSummaryView(
      warenkorb,
      formattedFullPrice,
      toggleView = {
        showOrderSummary = !showOrderSummary
        // Bestellung zurücksetzen
        if (!showOrderSummary) {
          warenkorb = emptyList()
        }
      },
      clearWarenkorb = { warenkorb = emptyList() }
      )

  }
}

fun updateWarenkorb(warenkorb: List<WarenkorbItem>, gericht: Gericht, anzahlAenderung: Int): List<WarenkorbItem> {
  val existingItem = warenkorb.find { it.gericht.id == gericht.id }
  return if (existingItem != null) {
    val updatedAnzahl = (existingItem.anzahl + anzahlAenderung).coerceAtLeast(0)
    if (updatedAnzahl > 0) {
      warenkorb.map { if (it.gericht.id == gericht.id) it.copy(anzahl = updatedAnzahl) else it }
    } else {
      warenkorb.filter { it.gericht.id != gericht.id }
    }
  } else if (anzahlAenderung > 0) {
    warenkorb + WarenkorbItem(gericht, anzahlAenderung)
  } else {
    warenkorb
  }
}

suspend fun sendBestellung(bestellung: Bestellung): HttpResponse {
  val client = HttpClient(CIO)
  val response: HttpResponse = client.post("http://localhost:8080/bestellung") {
    contentType(ContentType.Application.Json)
    setBody(Json.encodeToString(bestellung))
  }
  client.close()
  return response
}