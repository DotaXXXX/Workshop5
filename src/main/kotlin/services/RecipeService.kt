package services

import models.Recipe
import java.util.concurrent.atomic.AtomicInteger

class RecipeService {
    private val recipes = mutableListOf<Recipe>()
    private val idCounter = AtomicInteger()

    fun getAll(): List<Recipe> = recipes

    fun getById(id: Int): Recipe? = recipes.find { it.id == id }

    fun add(recipe: Recipe): Recipe {
        val newRecipe = recipe.copy(id = idCounter.incrementAndGet())
        recipes.add(newRecipe)
        return newRecipe
    }

    fun update(id: Int, updated: Recipe): Boolean {
        val index = recipes.indexOfFirst { it.id == id }
        return if (index != -1) {
            recipes[index] = updated.copy(id = id)
            true
        } else false
    }

    fun delete(id: Int): Boolean = recipes.removeIf { it.id == id }

    fun searchByIngredient(ingredient: String): List<Recipe> {
        return recipes.filter { recipe ->
            recipe.ingredients.any { it.name.contains(ingredient, ignoreCase = true) }
        }
    }
}