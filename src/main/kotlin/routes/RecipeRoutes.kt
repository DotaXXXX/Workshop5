package routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*
import models.Recipe
import services.RecipeService

fun Route.recipeRoutes(recipeService: RecipeService) {

    route("/recipes") {
        get {
            call.respond(recipeService.getAll())
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            val recipe = id?.let { recipeService.getById(it) }
            if (recipe != null) call.respond(recipe)
            else call.respond(HttpStatusCode.NotFound)
        }

        post {
            val recipe = call.receive<Recipe>()
            val newRecipe = recipeService.add(recipe)
            call.respond(HttpStatusCode.Created, newRecipe)
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            val updatedRecipe = call.receive<Recipe>()
            if (id != null && recipeService.update(id, updatedRecipe)) {
                call.respond(HttpStatusCode.OK)
            } else call.respond(HttpStatusCode.NotFound)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id != null && recipeService.delete(id)) {
                call.respond(HttpStatusCode.NoContent)
            } else call.respond(HttpStatusCode.NotFound)
        }

        get("/search") {
            val ingredient = call.request.queryParameters["ingredient"]
            if (ingredient != null) {
                val results = recipeService.searchByIngredient(ingredient)
                call.respond(results)
            } else call.respond(HttpStatusCode.BadRequest, "Missing query parameter: ingredient")
        }
    }
}