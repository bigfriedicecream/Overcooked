package com.twobrothers.overcooked.recipedetails.models

import com.twobrothers.overcooked.core.lookups.IngredientMeasurementUnit
import org.threeten.bp.Duration
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
    val interactive: List<InteractiveComponent>,
    val referenceName: String,
    val referenceUrl: String
)

sealed class Ingredient : Serializable

data class HeadingIngredient(
    val title: String
) : Ingredient()

data class FreeTextIngredient(
    val description: String
) : Ingredient()

data class QuantifiedIngredient(
    val quantity: Double,
    val measurementUnit: IngredientMeasurementUnit,
    val alternateMeasurementUnit: IngredientMeasurementUnit?,
    val food: Food,
    val endDescription: String
) : Ingredient()

sealed class InteractiveComponent : Serializable

data class InteractiveStep(
    val title: String,
    val body: String,
    val ingredients: List<Ingredient>?,
    val footnote: String,
    val timer: Duration?
) : InteractiveComponent()

data class CompletionStep(
    val title: String,
    val heroImageUrl: String
) : InteractiveComponent()