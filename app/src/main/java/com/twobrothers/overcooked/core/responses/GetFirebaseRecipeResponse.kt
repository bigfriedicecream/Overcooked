package com.twobrothers.overcooked.core.responses

import com.twobrothers.overcooked.core.lookups.LookupIngredientType
import com.twobrothers.overcooked.recipedetails.models.*
import com.twobrothers.overcooked.recipedetails.presentation.mapIngredientUnitType

data class GetFirebaseRecipeResponse(
    val data: FirebaseRecipeData
)

data class FirebaseRecipeData(
    val recipe: FirebaseRecipe,
    val food: HashMap<String, FirebaseFood>
) {
    fun toRecipe(): Recipe {
        return Recipe(
            id = recipe.id,
            title = recipe.title,
            heroImageUrl = recipe.heroImageUrl,
            serves = recipe.serves,
            prepTime = recipe.prepTime,
            cookTime = recipe.cookTime,
            ingredients = recipe.ingredients.map { it.toIngredient(recipe.serves, food) },
            method = recipe.method,
            interactive = recipe.interactive.map { it.toInteractiveStep(recipe.serves, food) },
            referenceName = recipe.referenceName,
            referenceUrl = recipe.referenceUrl
        )
    }
}

data class FirebaseRecipe(
    val id: String,
    val heroImageUrl: String,
    val title: String,
    val serves: Int,
    val prepTime: Int,
    val cookTime: Int,
    val ingredients: List<FirebaseIngredient>,
    val method: List<String>,
    val interactive: List<FirebaseInteractiveStep>,
    val referenceName: String,
    val referenceUrl: String
)

data class FirebaseIngredient(
    val ingredientTypeId: Int,
    val quantity: Double,
    val measurementUnitId: String,
    val alternateMeasurementUnitId: String?,
    val foodId: String,
    val description: String?,
    val title: String
) {
    fun toIngredient(serves: Int, food: HashMap<String, FirebaseFood>): Ingredient {
        return when (LookupIngredientType.getById(ingredientTypeId)) {
            LookupIngredientType.HEADING -> {
                HeadingIngredient(
                    title = title
                )
            }
            LookupIngredientType.QUANTIFIED -> {
                QuantifiedIngredient(
                    quantity = quantity / serves,
                    measurementUnit = mapIngredientUnitType(measurementUnitId),
                    alternateMeasurementUnit = if (alternateMeasurementUnitId != null) mapIngredientUnitType(
                        alternateMeasurementUnitId
                    ) else null,
                    food = food[foodId]?.toFood()
                        ?: throw IllegalStateException("Unable to find food for quantified ingredient"),
                    endDescription = description ?: ""
                )
            }
            else -> {
                FreeTextIngredient(
                    description = description ?: ""
                )
            }
        }
    }
}

data class FirebaseInteractiveStep(
    val title: String,
    val body: String,
    val ingredients: List<FirebaseIngredient>?,
    val footnote: String,
    val textDescription: String,
    val timer: Int
) {
    fun toInteractiveStep(serves: Int, food: HashMap<String, FirebaseFood>): InteractiveStep {
        return InteractiveStep(
            title = title,
            body = body,
            ingredients = ingredients?.map { it.toIngredient(serves, food) },
            footnote = footnote
        )
    }
}