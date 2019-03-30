package com.twobrothers.overcooked.models.recipe

data class RecipeModel (
    val id: String,
    val title: String,
    val prepTime: Int,
    val cookTime: Int,
    val ingredients: ArrayList<Ingredient>,
    val method: ArrayList<String>,
    val referenceUrl: String,
    val imageUrl: String,
    val lastUpdated: Long,
    val serves: Int?,
    val makes: Int?
) {
    interface Ingredient {
        val ingredientType: Int
    }

    data class Quantified (
            override val ingredientType: Int,
            val amount: Double,
            val unitIds: ArrayList<Int>,
            val foodId: String,
            val additionalDesc: String?
    ): Ingredient

    data class FreeText (
            override val ingredientType: Int,
            val description: String
    ): Ingredient

    data class Heading (
            override val ingredientType: Int,
            val title: String
    ): Ingredient
}