package com.twobrothers.overcooked.core.responses

import com.twobrothers.overcooked.core.lookups.LookupIngredientType
import com.twobrothers.overcooked.recipedetails.models.*
import com.twobrothers.overcooked.recipedetails.presentation.mapIngredientUnitType

data class FirebaseRecipe(
    val data: FirebaseRecipeData
)

data class FirebaseRecipeData(
    val recipe: FirebaseRecipeDetails,
    val food: HashMap<String, FirebaseFood>
) {
    fun toRecipe(): Recipe {
        return Recipe(
            id = recipe.id,
            title = recipe.title,
            serves = recipe.serves,
            prepTime = recipe.prepTime,
            cookTime = recipe.cookTime,
            ingredients = getMappedIngredients(),
            method = recipe.method
        )
    }

    private fun getMappedIngredients(): List<Ingredient> {
        return recipe.ingredients.map {
            when (LookupIngredientType.getById(it.ingredientTypeId)) {
                LookupIngredientType.HEADING -> {
                    HeadingIngredient(
                        title = it.description
                    )
                }
                LookupIngredientType.QUANTIFIED -> {
                    QuantifiedIngredient(
                        amount = it.amount / recipe.serves,
                        measurementUnit = mapIngredientUnitType(it.measurementUnitId) ?: throw IllegalStateException("Unable to map measurement type"),
                        food = food[it.foodId]?.toFood()
                            ?: throw IllegalStateException("Unable to find food for quantified ingredient"),
                        endDescription = it.description
                    )
                }
                else -> {
                    FreeTextIngredient(
                        description = it.description
                    )
                }
            }
        }
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
    val ingredientTypeId: Int,
    val amount: Double,
    val measurementUnitId: String,
    val foodId: String,
    val description: String
)