package com.twobrothers.overcooked.core.lookups

@SuppressWarnings("unused")
enum class LookupIngredientType(
    val id: Int
) {
    HEADING(0),
    QUANTIFIED(1),
    FREE_TEXT(2);

    companion object {
        fun getById(id: Int) = values().firstOrNull { it.id == id }
    }
}