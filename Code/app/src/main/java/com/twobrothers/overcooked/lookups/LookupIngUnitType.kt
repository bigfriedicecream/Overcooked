package com.twobrothers.overcooked.lookups

import java.util.*

enum class LookupIngUnitType constructor(val id:Int, val description:String, val shortName:String, val shortNamePlural:String) {
    Singular(0, "Singular", " ", " "),
    Grams(1, "Grams", "g ", "g "),
    Millilitres(2, "Millilitres", "ml ", "ml "),
    Tsp(3, "Tsp", " tsp ", " tsp "),
    Tbs(4, "Tbs", " Tbsp ", " Tbsp "),
    Cups(5, "Cups", " cup ", " cups "),
    Bunch(6, "Bunch", " bunch of ", " bunches of "),
    Rashers(7, "Rashers", " rasher of ", " rashes of "),
    Head(8, "Head", " head of ", " heads of "),
    Sprig(9, "Sprig", " sprig of ", " sprigs of "),
    Stalk(10, "Stalk", " stalk of ", " stalks of "),
    Sheets(11, "Sheets", " sheet of ", " sheets of "),
    Slice(12, "Slice", " slice of ", " slices of ");

    companion object {
        fun dataLookup(id:Int):LookupIngUnitType? {
            when (id) {
                LookupIngUnitType.Singular.id -> return LookupIngUnitType.Singular
                LookupIngUnitType.Grams.id -> return LookupIngUnitType.Grams
                LookupIngUnitType.Millilitres.id -> return LookupIngUnitType.Millilitres
                LookupIngUnitType.Tsp.id -> return LookupIngUnitType.Tsp
                LookupIngUnitType.Tbs.id -> return LookupIngUnitType.Tbs
                LookupIngUnitType.Cups.id -> return LookupIngUnitType.Cups
                LookupIngUnitType.Bunch.id -> return LookupIngUnitType.Bunch
                LookupIngUnitType.Rashers.id -> return LookupIngUnitType.Rashers
                LookupIngUnitType.Head.id -> return LookupIngUnitType.Head
                LookupIngUnitType.Sprig.id -> return LookupIngUnitType.Sprig
                LookupIngUnitType.Stalk.id -> return LookupIngUnitType.Stalk
                LookupIngUnitType.Sheets.id -> return LookupIngUnitType.Sheets
                LookupIngUnitType.Slice.id -> return LookupIngUnitType.Slice
            }
            return null
        }

        fun dataList():List<LookupIngUnitType> {
            return ArrayList(EnumSet.allOf(LookupIngUnitType::class.java))
        }
    }
}