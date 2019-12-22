package com.twobrothers.overcooked.recipedetails.presentation

import android.content.Context
import android.text.TextUtils
import androidx.core.text.HtmlCompat
import com.twobrothers.overcooked.core.IngredientMeasurementUnit
import com.twobrothers.overcooked.core.lookups.LookupMeasurementUnit
import com.twobrothers.overcooked.recipedetails.models.QuantifiedIngredient
import java.text.DecimalFormat
import kotlin.math.ceil
import kotlin.math.roundToInt

const val MAX_GRAMS_QUANTITY = 1000
const val MAX_MILLILITRES_QUANTITY = 1000

fun getQuantifiedIngredientReadableFormat(
    context: Context,
    ingredient: QuantifiedIngredient,
    serves: Int
): CharSequence {

    val quantity =
        if (ingredient.measurementUnit == IngredientMeasurementUnit.GRAMS || ingredient.measurementUnit == IngredientMeasurementUnit.MILLILITRES)
            ceil(ingredient.amount * serves)
        else ingredient.amount * serves

    val ingredientDisplay = when {
        // g to kg
        ingredient.measurementUnit == IngredientMeasurementUnit.GRAMS && quantity >= MAX_GRAMS_QUANTITY -> {
            val quantityDisplay = DecimalFormat("###.##").format(quantity / MAX_GRAMS_QUANTITY)
            val unitDisplay = context.resources.getQuantityString(
                IngredientMeasurementUnit.KILOGRAM.quantityStringId,
                quantity.toInt()
            )
            "$quantityDisplay$unitDisplay"
        }
        // ml to L
        ingredient.measurementUnit == IngredientMeasurementUnit.MILLILITRES && quantity >= MAX_MILLILITRES_QUANTITY -> {
            val quantityDisplay =
                DecimalFormat("###.##").format(quantity / MAX_MILLILITRES_QUANTITY)
            val unitDisplay = context.resources.getQuantityString(
                IngredientMeasurementUnit.LITRE.quantityStringId,
                quantity.toInt()
            )
            "$quantityDisplay$unitDisplay"
        }
        else -> {
            val quantityDisplay = HtmlCompat.fromHtml(
                quantity.toRationalHtmlString(),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
            val unitDisplay = context.resources.getQuantityString(
                ingredient.measurementUnit.quantityStringId,
                ceil(quantity).toInt()
            )
            "$quantityDisplay$unitDisplay"
        }
    }

    // singular/pluralization ingredient name
    val name = when {
        ingredient.measurementUnit == IngredientMeasurementUnit.SLICE -> ingredient.food.name.singular
        ingredient.measurementUnit == IngredientMeasurementUnit.ITEM -> {
            if (quantity > 1) ingredient.food.name.plural else ingredient.food.name.singular
        }
        ingredient.food.name.plural.isNotBlank() -> ingredient.food.name.plural
        else -> ingredient.food.name.singular
    }

    val endDescription =
        if (ingredient.endDescription.isNotBlank()) ", ${ingredient.endDescription}" else ""

    return TextUtils.concat(ingredientDisplay, name, endDescription)
}

/**
 * Converts a floating point number to a HTML fraction string
 * Rounds to the nearest supported fraction:
 * Fraction set: 1/10, 1/8, 1/4, 1/3, 1/2, 2/3, 3/4
 */
fun Double.toRationalHtmlString(): String {
    val intPart = this.toInt()
    val intPartString = if (intPart > 0) "$intPart&#65279;" else ""
    val fractionPart = this - intPart

    return when {
        intPart > 0 && fractionPart <= 0.0625f -> this.roundToInt().toString()
        intPart <= 0 && fractionPart <= 0.0625f -> "<sup>1</sup>&frasl;<sub>8</sub>"
        fractionPart > 0.0625f && fractionPart <= 0.1875f -> "$intPartString<sup>1</sup>&frasl;<sub>8</sub>"
        fractionPart > 0.1875f && fractionPart <= 0.29f -> "$intPartString<sup>1</sup>&frasl;<sub>4</sub>"
        fractionPart > 0.29f && fractionPart <= 0.415f -> "$intPartString<sup>1</sup>&frasl;<sub>3</sub>"
        fractionPart > 0.415f && fractionPart <= 0.58f -> "$intPartString<sup>1</sup>&frasl;<sub>2</sub>"
        fractionPart > 0.58f && fractionPart <= 0.705f -> "$intPartString<sup>2</sup>&frasl;<sub>3</sub>"
        fractionPart > 0.705f && fractionPart <= 0.875f -> "$intPartString<sup>3</sup>&frasl;<sub>4</sub>"
        else -> this.roundToInt().toString()
    }
}

/**
 * Maps a given firebase lookup unit type to an internal lookup
 *
 * @param unitType the string to style
 * @return [IngredientMeasurementUnit] the styled string
 */
fun mapIngredientUnitType(unitType: String): IngredientMeasurementUnit? {
    return when (LookupMeasurementUnit.getById(unitType)) {
        LookupMeasurementUnit.ITEM -> IngredientMeasurementUnit.ITEM
        LookupMeasurementUnit.GRAMS -> IngredientMeasurementUnit.GRAMS
        LookupMeasurementUnit.MILLILITRES -> IngredientMeasurementUnit.MILLILITRES
        LookupMeasurementUnit.TSP -> IngredientMeasurementUnit.TSP
        LookupMeasurementUnit.TBSP -> IngredientMeasurementUnit.TBS
        LookupMeasurementUnit.CUPS -> IngredientMeasurementUnit.CUPS
        LookupMeasurementUnit.BUNCH -> IngredientMeasurementUnit.BUNCH
        LookupMeasurementUnit.RASHERS -> IngredientMeasurementUnit.RASHERS
        LookupMeasurementUnit.HEAD -> IngredientMeasurementUnit.HEAD
        LookupMeasurementUnit.SPRIG -> IngredientMeasurementUnit.SPRIG
        LookupMeasurementUnit.STALK -> IngredientMeasurementUnit.STALK
        LookupMeasurementUnit.SHEETS -> IngredientMeasurementUnit.SHEETS
        LookupMeasurementUnit.SLICE -> IngredientMeasurementUnit.SLICE
        else -> null
    }
}