package com.twobrothers.overcooked.models.recipe

data class RecipeModel (
    val id: String,
    val title: String,
    val imageUrl: String,
    val serves: Int?,
    val makes: Int?,
    val prepTime: Int,
    val cookTime: Int,
    val method: ArrayList<String>,
    val ingredients: ArrayList<Any>
) {
    interface Ingredient {
        val ingredientType: Int
    }

    data class Heading (
            override val ingredientType: Int,
            val title: String
    ): Ingredient

    data class FreeText (
            override val ingredientType: Int,
            val description: String
    ): Ingredient

    data class Quantified (
            override val ingredientType: Int,
            val amount: Double,
            val unitIds: ArrayList<Int>,
            val food: RecipeResponseModel.Food,
            val additionalDesc: String?
    ): Ingredient
}