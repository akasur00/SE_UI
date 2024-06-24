package model

import kotlinx.serialization.Serializable



@Serializable
data class Bestellung(
  val id: Long,
  val kundenPosition: KundenPosition?,
  val mitarbeiterPosition: MitarbeiterPosition?,
  val entscheidungBestellung: EntscheidungBestellung?,
  val bestellPositionen: List<BestellPosition>,
  val erstellDatum: String
)

@Serializable
data class KundenPosition(val id: Long, val name: String)

@Serializable
data class MitarbeiterPosition(val id: Long, val name: String)

@Serializable
data class EntscheidungBestellung(val id: Long, val entscheidung: String)

@Serializable
data class BestellPosition(
  val bestellPositionId: Long,
  val gerichtID: Int,
  val preis: Double
)

