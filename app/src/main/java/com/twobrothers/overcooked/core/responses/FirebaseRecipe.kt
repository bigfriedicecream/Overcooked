package com.twobrothers.overcooked.core.responses

import com.twobrothers.overcooked.recipedetails.models.Recipe

data class FirebaseRecipe(
    val data: FirebaseRecipeData
)

data class FirebaseRecipeData(
    val recipe: FirebaseRecipeDetails
) {
    fun toRecipe(): Recipe {
        return Recipe(
            id = recipe.id,
            title = recipe.title,
            serves = recipe.serves,
            prepTime = recipe.prepTime,
            cookTime = recipe.cookTime,
            ingredients = listOf(),
            method = recipe.method
        )
    }
}

data class FirebaseRecipeDetails(
    val id: String,
    val title: String,
    val serves: Int,
    val prepTime: Int,
    val cookTime: Int,
    val ingredients: List<FirebaseIngredient>,
    val method: List<String>
)

data class FirebaseIngredient(
    val ingredientType: Int,
    val amount: Double,
    val measurementUnit: String,
    val food: String,
    val description: String
)