package model

import kotlinx.serialization.Serializable

@Serializable
data class Zutat(val name: String, val preis: Double)
