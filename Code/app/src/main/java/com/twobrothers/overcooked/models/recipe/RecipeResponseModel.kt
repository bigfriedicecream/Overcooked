package com.twobrothers.overcooked.models.recipe

import com.google.gson.JsonObject
import com.twobrothers.overcooked.models.food.FoodModel

data class RecipeResponseModel (
        val data: Data
) {
    data class Data (
            val recipe: Recipe,
            val food: HashMap<String, FoodModel>
    )

    data class Recipe (
            val id: String,
            val title: String,
            val prepTime: Int,
            val cookTime: Int,
            val ingredientSections: ArrayList<IngredientSection>,
            val method: ArrayList<String>,
            val referenceUrl: String,
            val imageUrl: String,
            val lastUpdated: Long,
            val serves: Int?,
            val makes: Int?
    )

    data class IngredientSection (
            val heading: String?,
            val ingredients: ArrayList<JsonObject>
    )
}



