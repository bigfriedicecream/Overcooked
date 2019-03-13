package com.twobrothers.overcooked.lookups

import java.util.*

enum class LookupIngredientUnitType constructor(val id:Int, val description:String, val singular:String, val plural:String) {
    Singular(0, "Singular", " ", " "),
    Grams(1, "Grams", "g ", "g "),
    Millilitres(2, "Millilitres", "ml ", "ml "),
    Tsp(3, "Tsp", " tsp ", " tsp "),
    Tbsp(4, "Tbsp", " Tbsp ", " Tbsp "),
    Cups(5, "Cup", " cup ", " cups "),
    Bunch(6, "Bunch", " bunch of ", " bunches of "),
    Rasher(7, "Rasher", " rasher of ", " rashes of "),
    Head(8, "Head", " head of ", " heads of "),
    Sprig(9, "Sprig", " sprig of ", " sprigs of "),
    Stalk(10, "Stalk", " stalk of ", " stalks of "),
    Sheet(11, "Sheets", " sheet of ", " sheets of "),
    Slice(12, "Slice", " slice of ", " slices of ");

    companion object {
        fun dataLookup(id:Int):LookupIngredientUnitType? {
            when (id) {
                Singular.id -> return Singular
                Grams.id -> return Grams
                Millilitres.id -> return Millilitres
                Tsp.id -> return Tsp
                Tbsp.id -> return Tbsp
                Cups.id -> return Cups
                Bunch.id -> return Bunch
                Rasher.id -> return Rasher
                Head.id -> return Head
                Sprig.id -> return Sprig
                Stalk.id -> return Stalk
                Sheet.id -> return Sheet
                Slice.id -> return Slice
            }
            return null
        }

        fun dataList():List<LookupIngredientUnitType> {
            return ArrayList(EnumSet.allOf(LookupIngredientUnitType::class.java))
        }
    }
}