package model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Gericht(
  val id: Int,
  val name: String,
  val nummer: Int,
  val preis: Double,
  val zutatenListe: List<Zutat>,
  val speisekarten: List<String>,
  val groesse: JsonElement
)