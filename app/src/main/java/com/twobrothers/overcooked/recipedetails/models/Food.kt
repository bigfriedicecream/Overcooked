package com.twobrothers.overcooked.recipedetails.models

import com.twobrothers.overcooked.core.lookups.IngredientMeasurementUnit
import java.io.Serializable

data class Food(
    val id: String,
    val name: Name,
    val conversions: List<Conversion>
) : Serializable

data class Name(
    val singular: String,
    val plural: String
) : Serializable

data class Conversion(
    val ratio: Double,
    val measurementUnit: IngredientMeasurementUnit
) : Serializable