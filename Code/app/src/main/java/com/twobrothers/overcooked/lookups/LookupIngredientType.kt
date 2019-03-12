package com.twobrothers.overcooked.lookups

import java.util.*

enum class LookupIngredientType constructor(val id:Int, val description:String) {
    Quantified(0, "Quantified"),
    FreeText(1, "FreeText"),
    Heading(2, "Heading");

    fun dataLookup(id:Int):LookupIngredientType? {
        when (id) {
            LookupIngredientType.Quantified.id -> return LookupIngredientType.Quantified
            LookupIngredientType.FreeText.id -> return LookupIngredientType.FreeText
            LookupIngredientType.Heading.id -> return LookupIngredientType.Heading
        }
        return null
    }

    fun dataList():List<LookupIngredientType> {
        return ArrayList(EnumSet.allOf(LookupIngredientType::class.java))
    }
}