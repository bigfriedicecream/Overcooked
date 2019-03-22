package com.twobrothers.overcooked.lookups

enum class LookupIngredientType (val id: Int) {
    None(-1),
    Quantified(0),
    FreeText(1),
    Heading(2);

    companion object {
        fun dataLookup(id: Int): LookupIngredientType {
            when (id) {
                Quantified.id -> return Quantified
                FreeText.id -> return FreeText
                Heading.id -> return Heading
            }
            return None
        }
    }
}