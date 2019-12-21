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
            cookTime = recipe.cookTime
        )
    }
}

data class FirebaseRecipeDetails(
    val id: String,
    val title: String,
    val serves: Int,
    val prepTime: Int,
    val cookTime: Int
)