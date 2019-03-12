package com.twobrothers.overcooked.models.recipe

import com.google.gson.JsonObject
import java.math.BigDecimal

data class RecipeResponseModel (
        val data: Data
) {
    data class Data (
            val recipe: Recipe,
            val food: HashMap<String, Food>
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

    data class Food (
            val id: String,
            val name: FoodName,
            val conversions: ArrayList<FoodConversion>
    )

    data class FoodName (
            val plural: String,
            val singular: String
    )

    data class FoodConversion (
            val ratio: Double,
            val unitId: Int
    )
}



