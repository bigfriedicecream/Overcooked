package com.twobrothers.overcooked.recipedetails.models

import com.twobrothers.overcooked.core.lookups.IngredientMeasurementUnit
import java.io.Serializable

data class Recipe(
    val id: String,
    val title: String,
    val heroImageUrl: String,
    val serves: Int,
    val prepTime: Int,
    val cookTime: Int,
    val ingredients: List<Ingredient>,
    val method: List<String>,
    val referenceName: String,
    val referenceUrl: String,
    val interactive: List<InteractiveStep>?
)

sealed class Ingredient

data class HeadingIngredient(
    val title: String
) : Ingredient()

data class FreeTextIngredient(
    val description: String
) : Ingredient()

data class QuantifiedIngredient(
    val amount: Double,
    val measurementUnit: IngredientMeasurementUnit,
    val alternateMeasurementUnit: IngredientMeasurementUnit?,
    val food: Food,
    val endDescription: String
) : Ingredient()

data class InteractiveStep(
    val title: String
) : Serializable