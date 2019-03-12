package com.twobrothers.overcooked.models.recipe

data class RecipeModel (
    val id: String,
    val title: String,
    val imageUrl: String,
    val method: ArrayList<String>,
    val ingredients: ArrayList<Any>,
    val food: HashMap<String, RecipeResponseModel.Food>
) {
    data class Heading (
            val title: String
    )

    data class FreeText (
            val description: String
    )

    data class Quantified (
            val amount: Int,
            val unitIds: ArrayList<Int>,
            val foodId: String
    )
}