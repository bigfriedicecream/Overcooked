package com.twobrothers.overcooked.recipedetails.models

import com.twobrothers.overcooked.core.lookups.IngredientMeasurementUnit

data class Food(
    val id: String,
    val name: Name,
    val conversions: List<Conversion>
)

data class Name(
    val singular: String,
    val plural: String
)

data class Conversion(
    val ratio: Double,
    val measurementUnit: IngredientMeasurementUnit
)