package com.twobrothers.overcooked.models.food

data class FoodModel(
        val id: String,
        val name: Name,
        val conversions: ArrayList<Conversion>
) {
    data class Name(
          val plural: String,
          val singular: String
    )

    data class Conversion(
            val ratio: Double,
            val unitId: Int
    )
}