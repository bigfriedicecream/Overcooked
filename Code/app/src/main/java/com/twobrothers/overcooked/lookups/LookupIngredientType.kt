package com.twobrothers.overcooked.lookups

import java.util.*

enum class LookupIngredientType (val id:Int, val description:String) {
    None(-1, "None"),
    Quantified(0, "Quantified"),
    FreeText(1, "FreeText"),
    Heading(2, "Heading");

    companion object {
        fun dataLookup(id:Int):LookupIngredientType {
            when (id) {
                Quantified.id -> return Quantified
                FreeText.id -> return FreeText
                Heading.id -> return Heading
            }
            return None
        }

        fun dataList():List<LookupIngredientType> {
            return ArrayList(EnumSet.allOf(LookupIngredientType::class.java))
        }
    }
}