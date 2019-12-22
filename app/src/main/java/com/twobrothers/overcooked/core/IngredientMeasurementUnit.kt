package com.twobrothers.overcooked.core

import com.twobrothers.overcooked.R

@SuppressWarnings("unused")
enum class IngredientMeasurementUnit(
    val quantityStringId: Int
) {
    ITEM(R.plurals.ingredient_unit_type_item),
    GRAMS(R.plurals.ingredient_unit_type_grams),
    MILLILITRES(R.plurals.ingredient_unit_type_millilitres),
    TSP(R.plurals.ingredient_unit_type_tsp),
    TBS(R.plurals.ingredient_unit_type_tbs),
    CUPS(R.plurals.ingredient_unit_type_cups),
    BUNCH(R.plurals.ingredient_unit_type_bunch),
    RASHERS(R.plurals.ingredient_unit_type_rashers),
    HEAD(R.plurals.ingredient_unit_type_head),
    SPRIG(R.plurals.ingredient_unit_type_sprig),
    STALK(R.plurals.ingredient_unit_type_stalk),
    SHEETS(R.plurals.ingredient_unit_type_sheets),
    SLICE(R.plurals.ingredient_unit_type_slice),
    KILOGRAM(R.plurals.ingredient_unit_type_kilogram),
    LITRE(R.plurals.ingredient_unit_type_litre)
}