package models

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    val name: String,
    val quantity: Double,
    val unit: String
)

@Serializable
data class Recipe(
    val id: Int,
    val name: String,
    val instructions: String,
    val ingredients: List<Ingredient>
)