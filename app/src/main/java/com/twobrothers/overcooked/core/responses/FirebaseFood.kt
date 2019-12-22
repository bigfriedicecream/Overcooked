package com.twobrothers.overcooked.core.responses

import com.twobrothers.overcooked.core.lookups.LookupMeasurementUnit
import com.twobrothers.overcooked.recipedetails.models.Conversion
import com.twobrothers.overcooked.recipedetails.models.Food
import com.twobrothers.overcooked.recipedetails.models.Name

data class FirebaseFood(
    val id: String,
    val name: FirebaseName,
    val conversions: List<FirebaseConversion>
) {
    fun toFood(): Food {
        return Food(
            id = id,
            name = name.toName(),
            conversions = conversions.map { it.toConversion() }
        )
    }
}

data class FirebaseName(
    val singular: String,
    val plural: String
) {
    fun toName() = Name(
        singular = singular,
        plural = plural
    )
}

data class FirebaseConversion(
    val ratio: Double,
    val unitId: String
) {
    fun toConversion() = Conversion(
        ratio = ratio,
        measurementType = LookupMeasurementUnit.getById(unitId) ?: LookupMeasurementUnit.UNIT
    )
}