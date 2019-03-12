package com.twobrothers.overcooked.models.recipe

data class RecipeModel (
    val id: String,
    val title: String,
    val imageUrl: String,
    val method: ArrayList<String>,
    val ingredients: ArrayList<Any>,
    val food: HashMap<String, RecipeResponseModel.Food>
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
            val amount: Int,
            val unitIds: ArrayList<Int>,
            val foodId: String
    ): Ingredient
}