package com.twobrothers.overcooked.recipedetails.presentation

import android.content.Context
import android.text.TextUtils
import androidx.core.text.HtmlCompat
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.core.IngredientMeasurementUnit
import com.twobrothers.overcooked.core.lookups.LookupMeasurementUnit
import com.twobrothers.overcooked.recipedetails.models.QuantifiedIngredient
import org.threeten.bp.Duration
import java.text.DecimalFormat
import kotlin.math.ceil
import kotlin.math.roundToInt


fun getQuantifiedIngredientReadableFormat(
    context: Context,
    ingredient: QuantifiedIngredient,
    serves: Int
): CharSequence {
    val quantity = ingredient.amount * serves
    val displayQuantity = when {
        ingredient.measurementUnit == IngredientMeasurementUnit.GRAMS && quantity >= 1000 -> {
            DecimalFormat("###.##").format(quantity / 1000)
        }
        ingredient.measurementUnit == IngredientMeasurementUnit.MILLILITRES && quantity >= 1000 -> {
            DecimalFormat("###.##").format(quantity / 1000)
        }
        ingredient.measurementUnit == IngredientMeasurementUnit.GRAMS -> {
            DecimalFormat("###.##").format(quantity)
        }
        ingredient.measurementUnit == IngredientMeasurementUnit.MILLILITRES -> {
            DecimalFormat("###.##").format(quantity)
        }
        else -> {
            HtmlCompat.fromHtml(
                quantity.toRationalHtmlString(),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        }
    }
    val measurementUnit = when {
        ingredient.measurementUnit == IngredientMeasurementUnit.GRAMS && quantity >= 1000 -> {
            context.resources.getQuantityString(
                IngredientMeasurementUnit.KILOGRAM.quantityStringId,
                ceil(quantity).toInt()
            )
        }
        ingredient.measurementUnit == IngredientMeasurementUnit.MILLILITRES && quantity >= 1000 -> {
            context.resources.getQuantityString(
                IngredientMeasurementUnit.MILLILITRES.quantityStringId,
                ceil(quantity).toInt()
            )
        }
        else -> {
            context.resources.getQuantityString(
                ingredient.measurementUnit.quantityStringId,
                ceil(quantity).toInt()
            )
        }
    }

    val name = when (ingredient.measurementUnit) {
        IngredientMeasurementUnit.SLICE -> ingredient.food.name.singular
        IngredientMeasurementUnit.ITEM -> {
            if (quantity > 1) ingredient.food.name.plural else ingredient.food.name.singular
        }
        else -> ingredient.food.name.plural
    }

    return when {
        // alternate measurement unit
        ingredient.alternateMeasurementUnit != null -> {
            val alternateRatio = ingredient.food.conversions.firstOrNull {
                it.measurementUnit == ingredient.alternateMeasurementUnit
            }?.ratio ?: 0.0
            val alternateQuantity = ingredient.amount * serves * alternateRatio
            val alternateQuantityDisplay = when {
                ingredient.alternateMeasurementUnit == IngredientMeasurementUnit.GRAMS && alternateQuantity >= 1000 -> {
                    DecimalFormat("###.##").format(alternateQuantity / 1000)
                }
                ingredient.alternateMeasurementUnit == IngredientMeasurementUnit.MILLILITRES && alternateQuantity >= 1000 -> {
                    DecimalFormat("###.##").format(alternateQuantity / 1000)
                }
                ingredient.alternateMeasurementUnit == IngredientMeasurementUnit.GRAMS -> {
                    DecimalFormat("###.##").format(alternateQuantity)
                }
                ingredient.alternateMeasurementUnit == IngredientMeasurementUnit.MILLILITRES -> {
                    DecimalFormat("###.##").format(alternateQuantity)
                }
                else -> {
                    HtmlCompat.fromHtml(
                        alternateQuantity.toRationalHtmlString(),
                        HtmlCompat.FROM_HTML_MODE_LEGACY
                    )
                }
            }
            val alternateMeasurementUnit = context.resources.getQuantityString(
                ingredient.alternateMeasurementUnit.quantityStringId,
                ceil(alternateQuantity).toInt()
            )
            TextUtils.concat(
                alternateQuantityDisplay,
                alternateMeasurementUnit,
                "(",
                displayQuantity,
                measurementUnit.trimEnd(),
                ") ",
                name
            )
        }
        else -> {
            TextUtils.concat(displayQuantity, measurementUnit, name)
        }
    }
}

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

fun getFormattedDuration(context: Context, duration: Duration): String {
    if (duration.toMinutes() <= 0) {
        return context.getString(R.string.duration_time_none)
    }

    val hours = duration.toHours().toInt()
    val mins = duration.minusHours(hours.toLong()).toMinutes().toInt()

    val hourString = if (hours == 0) "" else context.resources.getQuantityString(
        R.plurals.duration_hours,
        hours,
        hours
    )

    val minString =
        if (mins == 0) "" else "$mins ${context.getString(R.string.duration_time_format_minutes)}"

    return "$hourString $minString".trim()
}

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