package com.twobrothers.overcooked.models.recipe

data class RecipeModel (
    val id: String,
    val title: String,
    val imageUrl: String,
    val method: ArrayList<String>,
    val ingredients: ArrayList<Any>,
    val food: HashMap<String, RecipeResponseModel.Food>
) {
    interface IngredientType {
        val ingredientType: Int
    }

    data class Heading (
            override val ingredientType: Int,
            val title: String
    ): IngredientType

    data class FreeText (
            override val ingredientType: Int,
            val description: String
    ): IngredientType

    data class Quantified (
            override val ingredientType: Int,
            val amount: Int,
            val unitIds: ArrayList<Int>,
            val foodId: String
    ): IngredientType
}