package com.twobrothers.overcooked.models.recipe

import com.google.gson.JsonObject

data class RecipeResponseModel (
        val data: Data
) {
    data class Data (
            val recipe: Recipe
    )

    data class Recipe (
            val id: String,
            val title: String,
            val imageUrl: String,
            val method: ArrayList<String>,
            val ingredientSections: ArrayList<IngredientSection>
    )

    data class IngredientSection (
            val heading: String?,
            val ingredients: ArrayList<JsonObject>
    )
}



